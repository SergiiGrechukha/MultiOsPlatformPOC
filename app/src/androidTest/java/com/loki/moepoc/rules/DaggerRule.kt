package com.loki.moepoc.rules

import android.content.Context
import com.loki.moepoc.PocApp

import com.loki.moepoc.di.AppComponent
import com.loki.moepoc.di.DaggerTestAppComponent
import com.loki.moepoc.di.TestAppModule

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


class DaggerRule(private val context: Context) : TestRule {

    private val testComponent: AppComponent

    init {
        context.applicationContext as PocApp
        testComponent = DaggerTestAppComponent
                .builder()
                .testAppModule(TestAppModule()).build()
    }


    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                val application = context.applicationContext as PocApp
                // Set the TestComponent before the waiting_animator runs
                application.appComponent = testComponent
                base.evaluate()
            }
        }
    }
}
