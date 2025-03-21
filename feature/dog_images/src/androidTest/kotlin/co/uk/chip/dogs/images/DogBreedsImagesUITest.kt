package co.uk.chip.dogs.images

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.uk.chip.dog.domain.Dog
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class DogBreedsImagesUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun breedImageIsDisplaying() {
        val data = persistentListOf(
            "url1",
            "url2"
        )

        composeTestRule.setContent {
            DogBreedImagesScreen(
                imageUrls = data,
                isRefreshing = false,
                onRefresh = {}
            )
        }

        composeTestRule.onNodeWithTag(data[0]).assertIsDisplayed()
        composeTestRule.onNodeWithTag(data[1]).assertIsDisplayed()
    }
}