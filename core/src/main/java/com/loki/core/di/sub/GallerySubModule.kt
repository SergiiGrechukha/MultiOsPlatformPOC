package com.loki.core.di.sub

import com.loki.core.contract.GalleryContract
import com.loki.core.model.repository.ImageProvider
import com.loki.core.presenters.GalleryPresenter
import dagger.Module
import dagger.Provides

@Module
open class GallerySubModule(val view: GalleryContract.View) {

    @Provides
    open fun providePresenter(imageProvider: ImageProvider): GalleryContract.Presenter {
        return GalleryPresenter(imageProvider, view)
    }
}