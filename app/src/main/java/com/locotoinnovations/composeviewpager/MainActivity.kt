package com.locotoinnovations.composeviewpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.locotoinnovations.composeviewpager.ui.theme.ComposeViewPagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewmodel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeViewPagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainActivityViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = MainActivityUiState())
    Column(
        modifier = modifier,
    ) {
        Button(onClick = {
            viewModel.getData()
        }) {
            Text("Fetch Data")
        }

        if (uiState.isLoading) {
            Text("Loading...")
        } else {
            uiState.posts.forEach {
                Text(it.title)
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeViewPagerTheme {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}