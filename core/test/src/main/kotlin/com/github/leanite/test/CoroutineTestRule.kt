package com.github.leanite.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.RuleChain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

val instantLiveDataAndCoroutineRules: RuleChain
    get() = RuleChain
        .outerRule(CoroutineTestRule())
        .around(InstantTaskExecutorRule())

class CoroutineTestRule: TestWatcher() {

    @ExperimentalCoroutinesApi
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}