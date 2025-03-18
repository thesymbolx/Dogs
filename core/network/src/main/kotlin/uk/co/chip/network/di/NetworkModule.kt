package uk.co.chip.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import uk.co.chip.network.networkResultCallAdapter.NetworkResultCallAdapterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>
    ) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // lazy injection to prevent initializing OkHttp on the main thread. See network layer readme.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .build()
}