package com.demoxample.importantsamples.testingmodules

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.demoxample.importantsamples.R
import com.google.common.net.UrlEscapers
import kotlinx.android.synthetic.main.activity_glide_testing.*

class GlideTestingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_testing)
        Glide.with(this)
                .load(R.drawable.homeland)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(iv_left)

        Glide.with(this)
                .load(R.drawable.gotham)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
//                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(
//                        convertDpToPixel(52f, this).toInt()  , 0,
//                        RoundedCornersTransformation.CornerType.RIGHT)))
                .into(iv_right)


        val oauth_consumer_key = "xvz1evFS4wEEPTGEFPHBog"
        val oauth_nonce = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
        val oauth_signature = "tnnArxj06cWHq44gCs1OSKk/jLY="
        val oauth_signature_method = "HMAC-SHA1"
        val oauth_timestamp = "1318622958"
        val oauth_token = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"
        val oauth_version = "1.0"

        val esc = UrlEscapers.urlFormParameterEscaper()
        val header = "".plus("OAuth ")
                .plus("oauth_consumer_key").plus("=").plus(esc.escape(oauth_consumer_key))
                .plus(", ")
                .plus("oauth_nonce").plus("=").plus(esc.escape(oauth_nonce))
                .plus(", ")
                .plus("oauth_signature").plus("=").plus(esc.escape(oauth_signature))
                .plus(", ")
                .plus("oauth_signature_method").plus("=").plus(esc.escape(oauth_signature_method))
                .plus(", ")
                .plus("oauth_timestamp").plus("=").plus(esc.escape(oauth_timestamp))
                .plus(", ")
                .plus("oauth_token").plus("=").plus(esc.escape(oauth_token))
                .plus(", ")
                .plus("oauth_version").plus("=").plus(esc.escape(oauth_version))

        Log.e("OnCreate", " - - $header")

    }

    private fun convertDpToPixel(dp: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / 160f)
    }
}
