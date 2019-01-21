package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.content.Intent
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.LandingActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import kotlinx.android.synthetic.main.activity_landing.*
import java.lang.ref.WeakReference


class LandingView(val activity: LandingActivity) {
    val activityRef = WeakReference(activity)

    fun init(){
        activityRef.get()?.let {
            val rotate = AnimationUtils.loadAnimation(it, R.anim.rotate_landing_animation)
            rotate.interpolator = LinearInterpolator()
            it.image_landing_animated.startAnimation(rotate)
        }
    }

    fun startMainActivity() {
        activityRef.get()?.let {
            val intent = Intent(it, MainActivity::class.java)
            it.startActivity(intent)
        }

    }

    fun finishActivity() {
        activityRef.get()?.finish()
    }
}
