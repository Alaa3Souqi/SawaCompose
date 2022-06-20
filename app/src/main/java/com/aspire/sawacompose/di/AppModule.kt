package com.aspire.sawacompose.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.aspire.sawacompose.unitls.Constraints
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constraints.MY_PREFS_NAME,
            Context.MODE_PRIVATE
        )


}
