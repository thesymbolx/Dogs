package uk.co.chip.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uk.co.chip.network.endpoint.BreedsImageService
import uk.co.chip.network.endpoint.BreedsService

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    fun providesBreedService(retrofit: Retrofit) =
        retrofit.create(BreedsService::class.java)

    @Provides
    fun provideBreedsImageService(retrofit: Retrofit) =
        retrofit.create(BreedsImageService::class.java)
}