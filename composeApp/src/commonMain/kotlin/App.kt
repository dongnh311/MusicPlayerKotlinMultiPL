import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.KoinApplication
import org.koin.core.context.KoinContext
import org.koin.core.context.startKoin
import singleton.NetworkManager
import styles.MusicPlayerTheme
import view.HomeScreen
import view.MainScreen
import viewModel.MainViewModel


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    // Koin Di
    val module = AppModule()
    startKoin {
        modules(module.appModule, module.networkModule, module.viewModels)
        allowOverride(false)
    }
    // Di
    NetworkManager.iniAPIRest("https://www.google.com/")

    // Screen
    MusicPlayerTheme {
        Navigator(
            screen = MainScreen(),
            onBackPressed = { currentScreen ->
                println("Navigator: Pop screen #${currentScreen.key}")
                true
            }
        )
    }
}