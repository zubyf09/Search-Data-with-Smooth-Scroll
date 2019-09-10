package com.vivy.doctorsearch.di.module


import codenevisha.ir.mvvmwithdagger.data.db.AppDatabase
import com.google.gson.Gson
import com.vivy.doctorfinder.data.network.ApiService
import com.vivy.doctorfinder.data.repository.AppRepoImp
import com.vivy.doctorfinder.data.repository.AppRepository
import com.vivy.doctorsearch.core.Config
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder().baseUrl(Config.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(interceptor)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJavaCallbackFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService, database: AppDatabase): AppRepository {
        return AppRepoImp(apiService, database)
    }

}