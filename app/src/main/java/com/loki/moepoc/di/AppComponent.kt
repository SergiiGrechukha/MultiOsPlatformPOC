package com.loki.moepoc.di

import com.loki.core.di.AppModule
import com.loki.core.di.sub.GallerySubModule
import com.loki.core.di.sub.SubModuleFactory
import com.loki.moepoc.di.sub_comonents.GallerySubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent{

    fun getSubModuleFactory(): SubModuleFactory
    fun plus(subModule: GallerySubModule): GallerySubComponent
}