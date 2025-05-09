package gkk.app.sputnik.ui.screen.exstateflow

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gkk.app.sputnik.common.TextStyles

@Composable
fun ExLiveDataScreen(
    navController: NavController,
    viewModel: ExStateFlowViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("두 상태에 따라 새로운 상태 가공하기", style = TextStyles.Title)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "1. ID, PW 입력.\n2. 두 값을 관찰하고 버튼 상태를 가공.\n3. 버튼 상태를 자동으로 변경.",
            style = TextStyles.Content
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = uiState.id,
            onValueChange = { id ->
                viewModel.handleIntent(ExStateFlowIntent.SetId(id = id))
            },
            label = {
                Text("ID")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = uiState.pw,
            onValueChange = { pw ->
                viewModel.handleIntent(ExStateFlowIntent.SetPassword(password = pw))
            },
            label = {
                Text("PW")
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                Toast.makeText(context,"ID와 PW 모두 입력 : 로그인 가능", Toast.LENGTH_SHORT).show()
            },
            enabled = uiState.enabledBtnByCombine
        ) {
            Text("Login", style = TextStyles.Title)
        }
    }
}