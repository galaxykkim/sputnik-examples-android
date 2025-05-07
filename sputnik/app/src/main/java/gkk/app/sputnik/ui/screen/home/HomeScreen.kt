package gkk.app.sputnik.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.navigateTo) {
        state.navigateTo?.let {
            navController.navigate(it)
            viewModel.resetNavigation()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("GKK Lab. Examples")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.handleIntent(HomeIntent.NavigateToExampleLiveData)
        }) {
            Text("LiveData Example")
        }
    }
}