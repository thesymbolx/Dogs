package uk.co.chip.dogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import co.uk.chip.dogs.breeds.DogBreedsScreen
import dagger.hilt.android.AndroidEntryPoint
import co.uk.chip.dogs.design_system.DogsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DogBreedsScreen()
                }
            }
        }
    }
}