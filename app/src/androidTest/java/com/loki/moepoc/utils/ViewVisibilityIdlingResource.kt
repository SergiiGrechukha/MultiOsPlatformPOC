package com.loki.moepoc.utils

import android.support.test.espresso.IdlingResource
import android.view.View

class ViewVisibilityIdlingResource(private val view: View, private val expectedVisibility: Int) : IdlingResource {

    private var idle: Boolean = false
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.idle = false
        this.resourceCallback = null
    }

    override fun getName(): String {
        return ViewVisibilityIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        idle = idle || view.visibility == expectedVisibility

        if (idle) {
            if (resourceCallback != null) {
                resourceCallback!!.onTransitionToIdle()
            }
        }

        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

}