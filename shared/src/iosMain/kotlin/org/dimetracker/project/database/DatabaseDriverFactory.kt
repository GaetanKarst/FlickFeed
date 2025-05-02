package org.dimetracker.project.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import gaetan.karst.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(DailyPulseDatabase.Schema, "DailyPulse.Database.db")
    }
}