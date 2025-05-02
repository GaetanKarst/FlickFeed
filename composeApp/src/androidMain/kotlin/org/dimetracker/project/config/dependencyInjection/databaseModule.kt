package org.dimetracker.project.config.dependencyInjection

import app.cash.sqldelight.db.SqlDriver
import gaetan.karst.dailypulse.db.DailyPulseDatabase
import org.dimetracker.project.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}