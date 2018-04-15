package com.loki.moepoc

import com.loki.core.contract.GalleryContract
import com.loki.core.model.entity.Gallery
import com.loki.core.model.entity.GalleryImage
import com.loki.core.model.repository.ImageProvider
import com.loki.core.presenters.GalleryPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.subjects.BehaviorSubject
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.properties.Delegates


class GalleryPresenterTests {

    @Rule
    @JvmField
    val rule = RxRule()

    private var imagesSubject: BehaviorSubject<Gallery> by Delegates.notNull()
    private var mockImageProvider: ImageProvider by Delegates.notNull()
    private var mockView: GalleryContract.View by Delegates.notNull()

    @Before
    fun setupTest() {
        imagesSubject = BehaviorSubject.create()

        mockImageProvider = mock {
            on { getImagePreviewsByPage(any(), any()) } doReturn imagesSubject
        }
        mockView = mock {}
    }

    @Test
    @Throws(Exception::class)
    fun fetchImages_test() {

        val presenter = GalleryPresenter(mockImageProvider, mockView)

        var counter = 0

        presenter.onResume()
        presenter.subscribeForNewImages()
                .subscribe { counter++ }

        imagesSubject.onNext(Gallery(100, Collections.emptyList()))

        presenter.getImages()

        assertTrue("Images were not fetched once", counter == 1)

        presenter.getImages()

        assertTrue("Images were not fetched twice", counter == 2)
    }

    @Test
    @Throws(Exception::class)
    fun fetchOverLimit_test() {
        val presenter = GalleryPresenter(mockImageProvider, mockView)

        var counter = 0

        presenter.onResume()
        presenter.subscribeForNewImages()
                .subscribe { counter++ }

        val amountOfImages = 3

        imagesSubject.onNext(Gallery(amountOfImages, listOf(GalleryImage("123", "123"))))
        presenter.getImages()
        for (i in 1..amountOfImages + 2) {
            presenter.getNextImages()
        }

        assertEquals("Images were fetched more then needed", amountOfImages, counter)
    }

}