package com.matrix.link

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.matrix.link.di.components.ApplicationComponent
import com.matrix.link.di.components.DaggerApplicationComponent
import com.matrix.link.di.modules.ApplicationModule
import com.matrix.link.firebase.util.FireBaseConfig
import com.matrix.link.util.AppConstants.APP_NAME

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

        // Firebase setup
        val options = FirebaseOptions.Builder()
            .setApiKey(FireBaseConfig.API_KEY)
            .setApplicationId(FireBaseConfig.APP_ID)
            .setDatabaseUrl(FireBaseConfig.DATABASE_URL)
            .setProjectId(FireBaseConfig.PROJECT_ID)
            .setStorageBucket(FireBaseConfig.STORAGE_BUCKET)
            .build()
        FirebaseApp.initializeApp(this,options,APP_NAME)
    }


    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .appModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}