package com.loki.core.di.sub

import com.loki.core.contract.GalleryContract

open class SubModuleFactory  {

    open fun <T: GalleryContract.View> getModule(view: T): GallerySubModule = GallerySubModule(view)
}

inline fun <reified T: GalleryContract.View> SubModuleFactory.getSubModule(view: T): GallerySubModule {
    return this.getModule(view)
}
