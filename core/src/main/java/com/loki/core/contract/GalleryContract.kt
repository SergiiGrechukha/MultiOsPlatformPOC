package com.loki.core.contract

import io.reactivex.Observable

interface GalleryContract {
    interface View {
        fun onError(error: Throwable)
    }

    interface Presenter {
        fun onResume()
        fun onPause()
        fun subscribeForNewImages(): Observable<List<String>>
        fun getImages()
        fun getNextImages()
        fun getFullImages(): List<String>
    }
}