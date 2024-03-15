package com.olvera.thewarehouse.di

import com.olvera.thewarehouse.data.remote.PersonApi
import com.olvera.thewarehouse.util.Constants.Companion.BASE_PERSON_URL
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
            .baseUrl(BASE_PERSON_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesPesonApi(retrofit: Retrofit): PersonApi = retrofit.create(PersonApi::class.java)
}