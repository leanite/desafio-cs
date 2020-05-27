package com.github.leanite.test

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule: TestWatcher() {

    val  mockWebServer = MockWebServer()

    override fun starting(description: Description?) {
        super.starting(description)

        mockWebServer.start(8080)
    }

    override fun finished(description: Description?) {
        super.finished(description)

        mockWebServer.shutdown()
    }

}