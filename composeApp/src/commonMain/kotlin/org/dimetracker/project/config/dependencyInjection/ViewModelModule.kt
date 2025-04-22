package org.dimetracker.project.config.dependencyInjection

import org.dimetracker.project.articles.ArticlesViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { ArticlesViewModel(get()) }
}