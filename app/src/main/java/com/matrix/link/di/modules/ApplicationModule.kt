package com.matrix.link.di.modules

import android.app.Application
import com.matrix.link.LinkApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: LinkApplication) {
    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }
}
