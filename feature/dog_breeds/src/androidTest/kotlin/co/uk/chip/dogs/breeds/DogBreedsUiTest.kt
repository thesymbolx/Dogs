package co.uk.chip.dogs.breeds

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import co.uk.chip.dog.domain.Dog
import kotlinx.collections.immutable.persistentMapOf
import org.junit.Assert

import org.junit.Test

import org.junit.Rule

class DogBreedsUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun dogsAreDisplaying() {
        setContent()
        composeTestRule.onNodeWithText("AKITA").assertIsDisplayed()
        composeTestRule.onNodeWithText("BEAGLE").assertIsDisplayed()

        composeTestRule.onNodeWithText("BAKHARWAL").assertIsDisplayed()

        composeTestRule.onNodeWithText("BEAGLE").assertIsDisplayed()
        composeTestRule.onNodeWithText("INDIAN").assertIsDisplayed()
    }

    @Test
    fun dogBreedSelectable() {
        var selectedBreed: String? = null

        setContent { breed, _ ->
            selectedBreed = breed
        }

        composeTestRule.onNodeWithText("AKITA").performClick()
        Assert.assertEquals(selectedBreed, "akita")
    }

    @Test
    fun dogSubBreedSelectable() {
        var selectedBreed: String? = null
        var selectedSubBreed: String? = null

        setContent { breed, subBreed ->
            selectedBreed = breed
            selectedSubBreed = subBreed
        }

        composeTestRule.onNodeWithText("BAKHARWAL").performClick()
        Assert.assertEquals(selectedBreed, "bakharwal")
        Assert.assertEquals(selectedSubBreed, "indian")
    }

    private fun setContent(
        onBreedClick: (String, String?) -> Unit = { _, _ -> }
    ) {
        composeTestRule.setContent {
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

            DogBreedsScreen(
                breeds = data,
                isRefreshing = false,
                onRefresh = {},
                onBreedClick = onBreedClick
            )
        }
    }
}