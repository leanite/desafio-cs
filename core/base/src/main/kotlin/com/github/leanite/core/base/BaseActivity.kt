package com.github.leanite.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getActivityLayout())

        setupToolbar()
        setupViews()
        setupObservers()
        onSetupDone()
    }

    protected abstract fun getActivityLayout(): Int
    protected abstract fun setupToolbar()
    protected abstract fun setupViews()
    protected abstract fun setupObservers()
    protected abstract fun onSetupDone()
}