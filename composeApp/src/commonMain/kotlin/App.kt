import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import singleton.NetworkManager
import styles.MusicPlayerTheme
import view.HomeScreen
import view.MainScreen


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    startKoin {
        modules(AppModule.appModule, AppModule.networkModule)
        allowOverride(false)
    }
    NetworkManager.iniAPIRest("https://www.google.com/")
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