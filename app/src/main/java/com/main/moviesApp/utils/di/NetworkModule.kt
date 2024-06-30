package com.main.moviesApp.utils.di

import com.google.gson.GsonBuilder

import com.main.moviesApp.BuildConfig
import com.main.moviesApp.data.source.network.api.ApiService
import com.main.moviesApp.data.source.network.response.ApiKeyInterceptor
import com.main.moviesApp.data.source.network.response.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {

    @Provides
    fun providesOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun providesGsonConverterFactory(): Converter.Factory{
        return GsonConverterFactory.create(
            GsonBuilder()
                .create()
                )
    }




    @Provides
    @Singleton
    fun providesRetrofitApi(okHttpClient: OkHttpClient, converterFactory : Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)//BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())// to make callAdapter work with coroutines
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesIApiService(retrofit: Retrofit): ApiService {
        return retrofit.create()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.APIKEY)
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
                .method(request.method, request.body)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    fun providesOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder,
                             httpLoggingInterceptor: HttpLoggingInterceptor ,interceptor: Interceptor
    ): OkHttpClient {

        if (BuildConfig.BUILD_TYPE != "release") {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(interceptor)
        return okHttpClientBuilder
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .hostnameVerifier { _, _-> true }
            .build()
    }
}