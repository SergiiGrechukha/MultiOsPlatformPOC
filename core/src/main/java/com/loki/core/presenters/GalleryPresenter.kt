package com.loki.core.presenters

import com.loki.core.contract.GalleryContract
import com.loki.core.model.entity.Gallery
import com.loki.core.model.repository.ImageProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlin.properties.Delegates

class GalleryPresenter(
        private val imageProvider: ImageProvider,
        private val view: GalleryContract.View) : GalleryContract.Presenter {

    private var disposable: CompositeDisposable by Delegates.notNull()
    private var fetchSubject: BehaviorSubject<Any> by Delegates.notNull()
    private var newImagesSubject: BehaviorSubject<List<String>> by Delegates.notNull()
    private var gallerySubject: BehaviorSubject<Gallery> by Delegates.notNull()
    private var isLoading = false
    private var pageCounter = 1

    private var amountOfImagesInGallery: Int = Int.MAX_VALUE
    private var thereAreMore: Boolean = true

    override fun onResume() {
        fetchSubject = BehaviorSubject.create()
        newImagesSubject = BehaviorSubject.create()
        gallerySubject = BehaviorSubject.create()
        disposable = CompositeDisposable()
        disposable.add(
                fetchSubject
                        .observeOn(Schedulers.io())
                        .filter({ !isLoading && thereAreMore })
                        .flatMap { imageProvider.getImagePreviewsByPage(pageCounter) }
                        .scan { oldGallery: Gallery, newGallery: Gallery ->
                            updateGalleryList(oldGallery, newGallery)
                        }
                        .doOnNext({gallerySubject.onNext(it)})
                        .map { it.images }
                        .subscribe({
                            newImagesSubject.onNext(it.map { it.previewUrl })
                            isLoading = false
                            pageCounter++
                        }, {
                            view.onError(it)
                            isLoading = false
                        })
        )
    }

    private fun updateGalleryList(oldGallery: Gallery, newGallery: Gallery): Gallery {
        val result = oldGallery.images.toMutableList()
        result.addAll(newGallery.images)
        thereAreMore = result.size < newGallery.amountOfImagesInGallery
        return Gallery(amountOfImagesInGallery, result)
    }

    override fun getFullImages(): List<String> {
        return if (gallerySubject.hasValue()) {
            gallerySubject.value.images.map { it.fullImage }
        } else {
            listOf()
        }
    }

    override fun getImages() {
        if (newImagesSubject.hasValue()) {
            newImagesSubject.onNext(newImagesSubject.value)
        } else {
            fetchSubject.onNext(Any())
        }
    }

    override fun getNextImages() {
        fetchSubject.onNext(Any())
    }

    override fun subscribeForNewImages(): Observable<List<String>> {
        return newImagesSubject
    }

    override fun onPause() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}