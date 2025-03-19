package co.uk.chip.dogs.breeds

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import co.uk.chip.dog.domain.Dog
import kotlinx.collections.immutable.ImmutableMap
import uk.co.chip.dogs.ui.loading.LoadingScreen

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
private fun DogBreedsScreen(breeds: ImmutableMap<String, List<Dog>>) {
    Column {
        Header()

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            breeds.forEach { entry ->
                stickyHeader {
                    StickyCategory(entry.key)
                }

                items(items = entry.value, key = { it.breed + it.subBreed }) { breedName ->
                    DogBreed(breedName.breed, breedName.subBreed)
                }
            }
        }
    }
}

@Composable
private fun StickyCategory(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            text = title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun DogBreed(breed: String, subBreed: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = breed,
                style = MaterialTheme.typography.titleMedium
            )

            if (subBreed != null) {
                Text(
                    text = subBreed,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
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