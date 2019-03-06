package rosh.ivan.contries.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rosh.ivan.contries.feature.list.CountriesFragment

/**
 * Created by Ivan on 12/11/17.
 */

@Module
abstract class AndroidModule {
    @ContributesAndroidInjector
    abstract fun LoginSelectionFragmentInjector(): CountriesFragment
}
