package com.loki.core.di

import com.loki.core.di.sub.SubModuleFactory
import com.loki.core.model.repository.ImageProvider
import com.loki.core.model.repository.pixabay.PixaBayImageProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    fun imageProvider(): ImageProvider {
        return PixaBayImageProvider()
    }

    @Provides
    @Singleton
    fun provideSubModuleFactory(): SubModuleFactory {
        return getModuleFactory()
    }

    open fun getModuleFactory() = SubModuleFactory()
}