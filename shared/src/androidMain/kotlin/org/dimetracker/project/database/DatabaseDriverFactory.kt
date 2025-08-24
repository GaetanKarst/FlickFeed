package org.dimetracker.project.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import gaetan.karst.dailypulse.db.FlickFeedDatabase

// Database dependency graph and creation is different for android and IOS due to android context being needed in android
actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = FlickFeedDatabase.Schema,
            context = context,
            name = "FlickFeed.Database.db"
        )
}