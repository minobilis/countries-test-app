package rosh.ivan.contries.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ivan on 12/11/17.
 */

@Module
abstract class RxModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}
