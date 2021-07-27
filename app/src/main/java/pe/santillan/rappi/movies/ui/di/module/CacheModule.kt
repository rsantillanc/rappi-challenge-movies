package pe.santillan.rappi.movies.ui.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import pe.santillan.rappi.data.BuildConfig
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            BuildConfig.LIBRARY_PACKAGE_NAME,
            Context.MODE_PRIVATE
        )
    }
}