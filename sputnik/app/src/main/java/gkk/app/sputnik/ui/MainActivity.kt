package gkk.app.sputnik.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gkk.app.sputnik.common.Screen
import gkk.app.sputnik.ui.screen.home.HomeScreen
import gkk.app.sputnik.ui.screen.livedata.ExampleLiveDataScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.HOME) {
        composable(Screen.HOME) { HomeScreen(navController) }
        composable(Screen.EXAMPLE_LIVEDATA) { ExampleLiveDataScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}