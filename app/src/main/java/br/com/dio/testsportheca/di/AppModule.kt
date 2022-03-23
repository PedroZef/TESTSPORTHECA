package br.com.dio.testsportheca.di

import br.com.dio.testsportheca.data.Apifactory
import br.com.dio.testsportheca.data.repository.PlayerRepository
import br.com.dio.testsportheca.data.repository.PlayerRepositoryImpl
import br.com.dio.testsportheca.data.service.PlayersService
import br.com.dio.testsportheca.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{
        MainViewModel(repository = get())
    }
}
val repositoryModule = module{
    single<PlayerRepository> {
        PlayerRepositoryImpl(apiPlayer = get())
    }
}
val serviceModule = module{
    single {
        Apifactory.create(PlayersService::class.java)
    }
}

val listModules = listOf(serviceModule, repositoryModule, viewModelModule)