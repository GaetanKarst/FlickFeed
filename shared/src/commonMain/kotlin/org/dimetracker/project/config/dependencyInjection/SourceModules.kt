package org.dimetracker.project.config.dependencyInjection

import org.dimetracker.project.sources.SourcesViewModel
import org.dimetracker.project.sources.data.SourcesDatasource
import org.dimetracker.project.sources.data.SourcesRepository
import org.dimetracker.project.sources.data.SourcesService
import org.dimetracker.project.sources.data.SourcesUseCase
import org.koin.dsl.module

val sourceModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesDatasource> { SourcesDatasource(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
}