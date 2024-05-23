package com.olvera.thewarehouse.di

import com.google.gson.GsonBuilder
import com.olvera.thewarehouse.data.remote.StoreApi
import com.olvera.thewarehouse.util.Constants.Companion.BASE_STORE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    fun provideHttpClient() = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideStoreApi(@Named("storeApi") retrofit: Retrofit): StoreApi =
        retrofit.create(StoreApi::class.java)

    @Provides
    @Singleton
    @Named("storeApi")
    fun provideStoreApiRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_STORE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}