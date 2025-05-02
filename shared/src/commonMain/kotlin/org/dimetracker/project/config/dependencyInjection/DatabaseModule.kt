package org.dimetracker.project.config.dependencyInjection

import gaetan.karst.dailypulse.db.DailyPulseDatabase
import org.dimetracker.project.database.DatabaseDriverFactory
import org.koin.dsl.module

//class DatabaseModule(
//    private val driverFactory: DatabaseDriverFactory
//) {
//    suspend fun provideDatabase(): DailyPulseDatabase {
//        val driver = driverFactory.createDriver()
//        return DailyPulseDatabase(driver)
//    }
//}
//
//val databaseModule = module {
//    single { DatabaseDriverFactory(getOrNull()) }
//    single { DailyPulseDatabase(get<DatabaseDriverFactory>().createDriver()) }
//}