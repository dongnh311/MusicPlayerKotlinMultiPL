package di

import network.services.AccountServices
import network.services.EventServices
import network.services.MusicServices
import org.koin.dsl.module
import singleton.NetworkManager
import viewModel.AccountViewModel
import viewModel.EventDetailViewModel
import viewModel.HomeViewModel
import viewModel.MainViewModel
import viewModel.RankingViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class AppModule {
    val appModule = module {

    }

    val networkModule = module {
        single { NetworkManager.apiNetRest.ktorfit.create<AccountServices>() }
        single { NetworkManager.apiNetRest.ktorfit.create<MusicServices>() }
        single { NetworkManager.apiNetRest.ktorfit.create<EventServices>() }
    }

    val viewModels = module {
        factory { HomeViewModel() }
        factory { AccountViewModel() }
        factory { EventDetailViewModel() }
        factory { MainViewModel() }
        factory { RankingViewModel() }
    }
}