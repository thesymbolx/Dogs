package co.uk.chip.dogs.breeds

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.PersistentMap
import uk.co.chip.dogs.loading.LoadingScreen

@Composable
fun DogBreedsScreen(viewModel: DogBreedsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoadingScreen()
        else -> DogBreedsScreen(uiState.breeds)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DogBreedsScreen(breeds: ImmutableMap<String, List<String>>) {
    Column {
        Header()

        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            breeds.forEach { entry ->
                stickyHeader {
                    Text(entry.key, color = Color.Blue)
                }

                items(items = entry.value, key = { it }) { breedName ->
                    DogBreed(breedName, "Shitwho")
                }
            }
        }
    }
}

@Composable
private fun DogBreed(breed: String, subBreed: String?) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(text = breed)

            if(subBreed != null) {
                Text(text = subBreed)
            }
        }
    }
}

@Composable
private fun Header() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(
                        0.92f to Color.Black,
                        1f to Color.Transparent
                    ), blendMode = BlendMode.DstIn
                )
            },
        painter = painterResource(uk.co.chip.dogs.R.drawable.binyaminmellish),
        contentDescription = null
    )
}