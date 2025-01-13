package com.filippoengidashet.jetpackcomposenewsapp.data.di

import android.content.Context
import androidx.room.Room
import com.filippoengidashet.jetpackcomposenewsapp.data.model.Constants
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.NewsAppDatabase
import com.filippoengidashet.jetpackcomposenewsapp.data.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("api-key", Constants.Configs.API_KEY)
                    .build()
                chain.proceed(request)
            }.addInterceptor(
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Configs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsAppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NewsAppDatabase::class.java,
            name = NewsAppDatabase.DB_NAME
        ).build()
    }
}
