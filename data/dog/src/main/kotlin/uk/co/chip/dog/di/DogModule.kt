package uk.co.chip.dog.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uk.co.chip.dog.repository.DogRepository
import uk.co.chip.dog.repository.DogRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface DogModule {
    @Binds
    abstract fun bindDogRepository(impl: DogRepositoryImpl): DogRepository
}