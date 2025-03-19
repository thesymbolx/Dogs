package uk.co.chip.dogs.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DogApp() {
    DogAppBackground(modifier = Modifier) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets.navigationBars
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                NasaNavHost(navController)
            }
        }
    }
}

@Composable
fun DogAppBackground(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        content()
    }
}