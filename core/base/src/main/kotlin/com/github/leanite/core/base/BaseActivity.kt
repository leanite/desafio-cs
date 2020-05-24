package com.github.leanite.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())

        setupInjection()
        setupToolbar()
        setupViews()
        setupObservers()
        onSetupDone()
    }

    protected abstract fun getActivityLayout(): Int
    protected abstract fun setupInjection()
    protected abstract fun setupViews()
    protected abstract fun setupObservers()
    protected abstract fun onSetupDone()

    protected open fun setupToolbar() {
        setSupportActionBar(appToolbar)
    }
}