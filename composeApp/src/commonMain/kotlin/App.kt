import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import singleton.NetworkManager
import styles.MusicPlayerTheme
import view.HomeScreen
import view.MainScreen
import viewModel.MainViewModel


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
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