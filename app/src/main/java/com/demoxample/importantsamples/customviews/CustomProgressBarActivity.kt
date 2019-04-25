package com.demoxample.importantsamples.customviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.activity_custom_progress_bar.*

class CustomProgressBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_progress_bar)
        setUp()
    }

    private fun setUp() {
       val rotation = AnimationUtils.loadAnimation( this, R.anim.rotate)
        rotation.fillAfter = true
        pb7.startAnimation(rotation)
    }
}
