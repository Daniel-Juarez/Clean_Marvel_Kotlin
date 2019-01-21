package com.puzzlebench.clean_marvel_kotlin.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.LandingPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.LandingView

class LandingActivity : AppCompatActivity() {

    val presenter = LandingPresenter(LandingView((this)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        presenter.init()
    }
}
