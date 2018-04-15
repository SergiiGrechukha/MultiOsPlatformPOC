package com.loki.moepoc.di

import com.loki.core.di.AppModule
import com.loki.core.di.sub.SubModuleFactory
import com.nhaarman.mockito_kotlin.mock
import dagger.Module

@Module
class TestAppModule: AppModule(){

    override fun getModuleFactory(): SubModuleFactory {
        return mock()
    }
}