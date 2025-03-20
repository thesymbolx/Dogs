package uk.co.chip.dog.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uk.co.chip.dog.data.repository.BreedImageRepository
import uk.co.chip.dog.data.repository.BreedImageRepositoryImpl
import uk.co.chip.dog.data.repository.BreedRepository
import uk.co.chip.dog.data.repository.BreedRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface DogModule {
    @Binds
    abstract fun bindDogRepository(impl: BreedRepositoryImpl): BreedRepository

    @Binds
    abstract fun bindDogImageRepository(impl: BreedImageRepositoryImpl): BreedImageRepository
}