package di

import network.services.AccountServices
import network.services.MusicServices
import org.koin.dsl.module
import singleton.NetworkManager

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
object AppModule {
    val appModule = module {

    }

    val networkModule = module {
        single { NetworkManager.apiNetRest.ktorfit.create<AccountServices>() }
        single { NetworkManager.apiNetRest.ktorfit.create<MusicServices>() }
    }
}