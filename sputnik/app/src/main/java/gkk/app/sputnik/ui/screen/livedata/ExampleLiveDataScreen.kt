package gkk.app.sputnik.ui.screen.livedata

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gkk.app.sputnik.ui.screen.home.HomeViewModel

@Composable
fun ExampleLiveDataScreen(
    navController: NavController,
    viewModel: ExampleLiveDataViewModel = hiltViewModel()
) {
    Column {
        Text("Example - LiveData")
    }
}