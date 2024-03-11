import androidx.compose.runtime.*
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import styles.MusicPlayerTheme
import view.HomeScreen


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    startKoin {
        modules(AppModule.appModule)
        allowOverride(false)
    }
    MusicPlayerTheme {
        HomeScreen {

        }
    }
}