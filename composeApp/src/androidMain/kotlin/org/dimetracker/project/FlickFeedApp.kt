package org.dimetracker.project

import android.app.Application
import android.util.Log
import org.dimetracker.project.config.dependencyInjection.databaseModule
import org.dimetracker.project.config.dependencyInjection.sharedModule
import org.dimetracker.project.config.dependencyInjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.io.PrintStream

class FlickFeedApp : Application() {

    override fun onCreate() {
        super.onCreate()
        allowDebugPrint()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedModule + viewModelModule + databaseModule

        startKoin {
            androidContext(this@FlickFeedApp)
            modules(modules)
        }
    }

    private fun allowDebugPrint() {
        System.setOut(object : PrintStream(System.out) {
            override fun println(msg: String?) {
                Log.d("System.out", msg ?: "")
            }
        })
    }
}