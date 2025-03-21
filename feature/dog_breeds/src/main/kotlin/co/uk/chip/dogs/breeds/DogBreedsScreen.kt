package co.uk.chip.dogs.breeds

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.uk.chip.dog.domain.Dog
import co.uk.chip.dogs.design_system.DogsTheme
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import uk.co.chip.dogs.ui.ErrorScreen
import uk.co.chip.dogs.ui.LoadingScreen

@Composable
fun DogBreedsScreen(
    viewModel: DogBreedsViewModel = hiltViewModel(),
    onBreedClick: (breed: String, subBreed: String?) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isError -> ErrorScreen()
        uiState.isLoading -> LoadingScreen()
        else -> DogBreedsScreen(
            breeds = uiState.breeds,
            isRefreshing = uiState.isRefreshing,
            onRefresh = viewModel::onRefresh,
            onBreedClick = onBreedClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DogBreedsScreen(
    breeds: ImmutableMap<String, List<Dog>>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onBreedClick: (breed: String, subBreed: String?) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        Column {
            Header()

            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                breeds.forEach { entry ->
                    stickyHeader {
                        StickyCategory(entry.key)
                    }

                    items(items = entry.value, key = { it.id }) { breedName ->
                        DogBreed(breedName.breed, breedName.subBreed, onBreedClick)
                    }
                }

                item {
                    Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
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
private fun DogBreed(
    breed: String,
    subBreed: String?,
    onBreedClick: (breed: String, subBreed: String?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable {
                onBreedClick(breed.lowercase(), subBreed?.lowercase())
            }
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
        painter = painterResource(uk.co.chip.dogs.R.drawable.dog1),
        contentDescription = null
    )
}

@Preview
@Composable
fun DogBreedsScreenPreview() {
    val data =
        persistentMapOf(
            "A" to listOf(
                Dog(
                    id = "1",
                    breed = "AKITA",
                    subBreed = null
                )
            ),
            "B" to listOf(
                Dog(
                    id = "2",
                    breed = "BEAGLE",
                    subBreed = null
                ),
                Dog(
                    id = "3",
                    breed = "BAKHARWAL",
                    subBreed = "INDIAN"
                )
            )
        )

    DogsTheme {
        DogBreedsScreen(
            breeds = data,
            isRefreshing = false,
            onRefresh = {},
            onBreedClick = { _, _ -> }
        )
    }
}