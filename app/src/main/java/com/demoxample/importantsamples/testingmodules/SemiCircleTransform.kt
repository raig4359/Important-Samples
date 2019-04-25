package com.demoxample.importantsamples.testingmodules

import android.graphics.*
import android.os.Build
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.util.*
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by Gaurav on 29-03-2019.
 */
class SemiCircleTransform(val x: Float, val y: Float, val width: Int, height: Int) : BitmapTransformation() {
    // The version of this transformation, incremented to correct an error in a previous version.
    // See #455.
    private val VERSION = 1
    private val ID = "com.firstnotify.warcircle.load.resource.bitmap.SemiCircle.$VERSION"
    private var ID_BYTES: ByteArray? = null

    private val PAINT_FLAGS = Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG
    private val CIRCLE_CROP_PAINT_FLAGS = PAINT_FLAGS or Paint.ANTI_ALIAS_FLAG
    private val CIRCLE_CROP_SHAPE_PAINT = Paint(CIRCLE_CROP_PAINT_FLAGS)
    private val CIRCLE_CROP_BITMAP_PAINT: Paint

    init {
        try {
            ID_BYTES = ID.toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    init {
        CIRCLE_CROP_BITMAP_PAINT = Paint(CIRCLE_CROP_PAINT_FLAGS)
        CIRCLE_CROP_BITMAP_PAINT.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    // Bitmap doesn't implement equals, so == and .equals are equivalent here.
    override fun transform(
            pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return circleCrop(pool, toTransform, outWidth, outHeight)
    }

    override fun equals(o: Any?): Boolean {
        return o is SemiCircleTransform
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    fun circleCrop(pool: BitmapPool, inBitmap: Bitmap, destWidth: Int, destHeight: Int): Bitmap {

        val destMinEdge = Math.min(destWidth, destHeight)
        val radius = destMinEdge / 2f

        val srcWidth = inBitmap.width
        val srcHeight = inBitmap.height

        val scaleX = destMinEdge / srcWidth.toFloat()
        val scaleY = destMinEdge / srcHeight.toFloat()


        val maxScale = Math.max(scaleX, scaleY)


        val scaledWidth = maxScale * srcWidth
        val scaledHeight = maxScale * srcHeight

        val left = (destMinEdge - scaledWidth) / 2f
        val top = (destMinEdge - scaledHeight) / 2f

        //val left = (destMinEdge) / 2f
        //val top = (destMinEdge) / 2f

        //val destRect = RectF(left, top, left  , top  )
        //val destRect = RectF(left, top, (2 * destWidth).toFloat(), (2 * destHeight).toFloat())
//          val destRect = RectF(left, top, left + scaledWidth, top + scaledHeight)
        val destRect = RectF(x,   y, 300f, 300f)

        // Alpha is required for this transformation.
        val toTransform = getAlphaSafeBitmap(pool, inBitmap)

        val outConfig = getAlphaSafeConfig(inBitmap)
        //val result = pool.get(destMinEdge, destMinEdge, outConfig)
        val result = pool.get(600, 600, outConfig)
        result.setHasAlpha(true)

        if (MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL)) {
            BITMAP_DRAWABLE_LOCK.lock()
        }

        try {
            val bitmap = Bitmap.createBitmap(600, 600, outConfig)
            val canvas = Canvas(bitmap)
            // Draw a circle
            //            canvas.drawCircle(radius, radius, radius, CIRCLE_CROP_SHAPE_PAINT);
            //canvas.drawArc(destRect, 90f, 180f, true, CIRCLE_CROP_SHAPE_PAINT)
            // Draw the bitmap in the circle
            //canvas.drawBitmap(toTransform, null /*paint*/, destRect, CIRCLE_CROP_BITMAP_PAINT)
            val paint = Paint()
            paint.color = Color.YELLOW
            canvas.drawColor(Color.BLUE)
            canvas.drawRect(destRect, paint)
            //clear(canvas)
        } finally {
            if (MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL)) {
                BITMAP_DRAWABLE_LOCK.unlock()
            }
        }

        if (toTransform != inBitmap) {
            pool.put(toTransform)
        }

        return result
    }

    private fun clear(canvas: Canvas) {
        canvas.setBitmap(null)
    }

    private fun getAlphaSafeBitmap(pool: BitmapPool, maybeAlphaSafe: Bitmap): Bitmap {
        val safeConfig = getAlphaSafeConfig(maybeAlphaSafe)
        if (safeConfig == maybeAlphaSafe.config) {
            return maybeAlphaSafe
        }

        val argbBitmap = pool.get(maybeAlphaSafe.width, maybeAlphaSafe.height, safeConfig)
        Canvas(argbBitmap).drawBitmap(maybeAlphaSafe, 0f /*left*/, 0f /*top*/, null)

        // We now own this Bitmap. It's our responsibility to replace it in the pool outside this method
        // when we're finished with it.
        return argbBitmap
    }

    private fun getAlphaSafeConfig(inBitmap: Bitmap): Bitmap.Config {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Avoid short circuiting the sdk check.
            if (Bitmap.Config.RGBA_F16 == inBitmap.config) { // NOPMD
                return Bitmap.Config.RGBA_F16
            }
        }

        return Bitmap.Config.ARGB_8888
    }

    companion object {
        private val Tag = "SemiCircle"

        private val MODELS_REQUIRING_BITMAP_LOCK = HashSet(
                Arrays.asList(
                        // Moto X gen 2
                        "XT1085",
                        "XT1092",
                        "XT1093",
                        "XT1094",
                        "XT1095",
                        "XT1096",
                        "XT1097",
                        "XT1098",
                        // Moto G gen 1
                        "XT1031",
                        "XT1028",
                        "XT937C",
                        "XT1032",
                        "XT1008",
                        "XT1033",
                        "XT1035",
                        "XT1034",
                        "XT939G",
                        "XT1039",
                        "XT1040",
                        "XT1042",
                        "XT1045",
                        // Moto G gen 2
                        "XT1063",
                        "XT1064",
                        "XT1068",
                        "XT1069",
                        "XT1072",
                        "XT1077",
                        "XT1078",
                        "XT1079"
                )
        )

        private val BITMAP_DRAWABLE_LOCK = ReentrantLock()
    }

}