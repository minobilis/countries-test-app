package rosh.ivan.contries.common

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import rosh.ivan.contries.di.DaggerApplicationComponent


class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
}