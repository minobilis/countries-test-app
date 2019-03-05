package maatayim.com.brainsway.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Reusable
import rosh.ivan.contries.common.App

/**
 * Created by Ivan on 12/11/17.
 */

@Module
abstract class ContextModule {

    @Reusable
    @Binds
    abstract fun application(app: App): Context
}
