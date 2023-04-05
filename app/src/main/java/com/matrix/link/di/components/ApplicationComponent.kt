package com.matrix.link.di.components

import com.matrix.link.LinkApplication
import com.matrix.link.di.modules.ApplicationModule
import com.matrix.link.di.modules.NetworkModule
import com.matrix.link.ui.sample.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: ApplicationModule): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: LinkApplication)
    fun inject(mainActivity: MainActivity)
}