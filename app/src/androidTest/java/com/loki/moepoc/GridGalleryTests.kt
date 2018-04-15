package com.loki.moepoc

import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.view.View.GONE
import com.loki.core.contract.GalleryContract
import com.loki.core.di.sub.GallerySubModule
import com.loki.moepoc.rules.DaggerRule
import com.loki.moepoc.utils.RecyclerViewItemCountAssertion
import com.loki.moepoc.utils.ViewVisibilityIdlingResource
import com.nhaarman.mockito_kotlin.*
import io.reactivex.subjects.BehaviorSubject
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule


class GridGalleryTests {

    private val daggerRule = DaggerRule(InstrumentationRegistry.getTargetContext())
    private val rule = ActivityTestRule(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    val chain: TestRule = RuleChain.outerRule(daggerRule).around(rule)

    @Test
    fun gridGalleryValidity_test() {

        prepareMocks()

        rule.launchActivity(null)

        // wait for images in rv to load
        val idlingRegistry = IdlingRegistry.getInstance()
        val idlingResource = ViewVisibilityIdlingResource(rule.activity.findViewById(R.id.pb_loading), GONE)
        idlingRegistry.register(idlingResource)

        //check if imaged loaded
        onView(withId(R.id.iv_image)).check(matches(isDisplayed()))
        //check if item amount is correct
        onView(withId(R.id.rv_gallery)).check(RecyclerViewItemCountAssertion(1))

        idlingRegistry.unregister(idlingResource)

    }

    private fun prepareMocks() {
        val pocApp = getInstrumentation().targetContext.applicationContext as PocApp
        val path = Uri.parse("android.resource://${pocApp.packageName}/${R.mipmap.ic_launcher}").toString()

        val imagesSubject = BehaviorSubject.create<List<String>>()
        val mockPresenter = mock<GalleryContract.Presenter> {
            on { subscribeForNewImages() } doReturn imagesSubject
            on { getImages() } doAnswer { imagesSubject.onNext(listOf(path)) }
            on { getNextImages() } doAnswer { imagesSubject.onNext(listOf(path)) }
        }

        val mockModule = mock<GallerySubModule> {
            on { providePresenter(any()) } doReturn mockPresenter
        }


        val mockFactory = pocApp.appComponent.getSubModuleFactory()

        whenever(mockFactory.getModule(any<GalleryContract.View>())).thenReturn(mockModule)
    }
}

