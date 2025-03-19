package co.uk.chip.dogs.breeds

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DogBreedsScreen(viewModel: DogBreedsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Text("${uiState.breeds}")
}

@Composable
fun DogBreedsScre