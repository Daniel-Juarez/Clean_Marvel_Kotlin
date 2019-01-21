package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.os.Handler

class LandingPresenter(val view: LandingView) {
    val DURATION_TIME_LANDING_ANIMATION:Long = 6000

    fun init() {
        view.init()
        addDelayedFunction()
    }

    fun addDelayedFunction() {
        Handler().postDelayed({
            view.startMainActivity()
            view.finishActivity()
        }, DURATION_TIME_LANDING_ANIMATION)
    }
}
