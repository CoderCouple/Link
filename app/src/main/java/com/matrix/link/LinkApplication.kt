package com.matrix.link

import android.app.Application
import com.facebook.stetho.Stetho
import com.matrix.link.di.components.ApplicationComponent
import com.matrix.link.di.components.DaggerApplicationComponent
import com.matrix.link.di.modules.ApplicationModule

class LinkApplication : Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        fun getAppComponent(): ApplicationComponent {
            return applicationComponent
        }
    }

    override fun onCreate() {
        super.onCreate()

//         https://github.com/facebook/stetho/issues/696
//        Stetho.initialize(Stetho.newInitializerBuilder(this)
//            .enableDumpapp(
//                Stetho.defaultDumperPluginsProvider(this))
//            .enableWebKitInspector(
//                Stetho.defaultInspectorModulesProvider(this))
//            .build())


        injectDependencies()
    }


    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .appModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}