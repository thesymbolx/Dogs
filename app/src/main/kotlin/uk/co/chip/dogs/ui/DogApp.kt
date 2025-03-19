package uk.co.chip.dogs.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import co.uk.chip.dogs.design_system.DogsTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DogApp() {
    val navController = rememberNavController()

    DogsTheme(dynamicColor = false) {
        DogAppBackground(modifier = Modifier) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                contentWindowInsets = WindowInsets.navigationBars
            ) {  _ ->
                DogNavHost(navController)
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