package com.olvera.thewarehouse.di

import android.app.Application
import android.content.Context
import com.olvera.thewarehouse.data.remote.PersonApi
import com.olvera.thewarehouse.usecase.EmailMatcher
import com.olvera.thewarehouse.usecase.EmailMatcherImpl
import com.olvera.thewarehouse.usecase.SignupUseCases
import com.olvera.thewarehouse.usecase.ValidEmptyFields
import com.olvera.thewarehouse.usecase.ValidateEmailUseCase
import com.olvera.thewarehouse.usecase.ValidatePasswordUseCase
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

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideSignupUseCases(
        emailMatcher: EmailMatcher
    ): SignupUseCases {
        return SignupUseCases(
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            validEmptyFields = ValidEmptyFields()
        )
    }

    @Singleton
    @Provides
    fun providesPersonApi(retrofit: Retrofit): PersonApi = retrofit.create(PersonApi::class.java)


}