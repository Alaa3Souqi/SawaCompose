package com.aspire.sawacompose

import android.app.Application
import android.content.Context
import androidx.activity.ComponentActivity
import com.airbnb.mvrx.Mavericks
import com.aspire.sawacompose.di.AppComponent
import com.aspire.sawacompose.di.DaggerAppComponent
import com.aspire.sawacompose.repo.SettingRepo
import com.aspire.sawacompose.unitls.Constraints

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        Mavericks.initialize(this)
    }

}

fun ComponentActivity.appComponent(): AppComponent {
    return (application as BaseApplication).appComponent
}