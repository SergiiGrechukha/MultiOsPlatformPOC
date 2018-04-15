package com.loki.moepoc.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestAppModule::class))
interface TestAppComponent: AppComponent {
}