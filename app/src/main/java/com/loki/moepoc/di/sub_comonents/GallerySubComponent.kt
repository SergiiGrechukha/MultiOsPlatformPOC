package com.loki.moepoc.di.sub_comonents

import com.loki.core.di.sub.GallerySubModule
import com.loki.moepoc.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [(GallerySubModule::class)])
interface GallerySubComponent{
    fun inject(view: MainActivity)
}