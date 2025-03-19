package uk.co.chip.dogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.uk.chip.dogs.breeds.DogBreedsScreen
import dagger.hilt.android.AndroidEntryPoint
import co.uk.chip.dogs.design_system.DogsTheme
import uk.co.chip.dogs.ui.DogApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

//            Column {
//                Text("number 1", color = Color.Black)
//                Text("dalee dadiklahsiodjasioduj", color = Color.Black)
//
//                Text("dalee dadiklahsiodjasioduj", color = Color.Black)
//
//                Text("dalee dadiklahsiodjasioduj", color = Color.Black)
//
//                Text("dalee dadiklahsiodjasioduj", color = Color.Black)
//                Text("dalee dadiklahsiodjasioduj", color = Color.Black)
//                Text("dalee dadiklahsiodjasioduj", color = Color.Black)
//
//
//                Box(Modifier.size(600.dp).background(Color.Blue), ) {  }
//
//
//                Surface(
//                    color = Color.Red,
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//
//                }
//

  //          }


            DogApp()
        }
    }
}