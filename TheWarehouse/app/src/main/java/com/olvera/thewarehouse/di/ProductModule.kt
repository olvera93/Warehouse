package com.olvera.thewarehouse.di

import com.olvera.thewarehouse.data.remote.StoreApi
import com.olvera.thewarehouse.util.Constants.Companion.BASE_STORE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Singleton
    @Provides
    fun provideStoreApi(@Named("storeApi") retrofit: Retrofit): StoreApi =
        retrofit.create(StoreApi::class.java)

    @Provides
    @Singleton
    @Named("storeApi")
    fun provideStoreApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_STORE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}