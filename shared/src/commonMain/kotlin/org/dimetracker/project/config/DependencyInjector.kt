package org.dimetracker.project.config

import androidx.lifecycle.ViewModel
import org.dimetracker.project.articles.ArticlesViewModel
import org.dimetracker.project.articles.data.ArticlesService
import org.dimetracker.project.articles.data.ArticlesUseCase
import org.koin.dsl.module

val modules = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}