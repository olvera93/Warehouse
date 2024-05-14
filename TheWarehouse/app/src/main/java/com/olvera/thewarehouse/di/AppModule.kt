package com.olvera.thewarehouse.di

import android.app.Application
import android.content.Context
import com.olvera.thewarehouse.data.remote.WarehouseApi
import com.olvera.thewarehouse.util.Constants.Companion.BASE_WAREHOUSE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesPersonRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_WAREHOUSE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideWarehouseApi(retrofit: Retrofit): WarehouseApi = retrofit.create(WarehouseApi::class.java)
}