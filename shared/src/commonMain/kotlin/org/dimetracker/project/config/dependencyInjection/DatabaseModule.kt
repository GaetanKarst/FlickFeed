package org.dimetracker.project.config.dependencyInjection

//class DatabaseModule(
//    private val driverFactory: DatabaseDriverFactory
//) {
//    suspend fun provideDatabase(): FlickFeedDatabase {
//        val driver = driverFactory.createDriver()
//        return FlickFeedDatabase(driver)
//    }
//}
//
//val databaseModule = module {
//    single { DatabaseDriverFactory(getOrNull()) }
//    single { FlickFeedDatabase(get<DatabaseDriverFactory>().createDriver()) }
//}