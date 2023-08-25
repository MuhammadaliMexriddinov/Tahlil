package uz.catsi.tahlil.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.catsi.tahlil.data.remote.api.TestApi
import uz.catsi.tahlil.utils.BASE_URL_MOBILE
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL_MOBILE)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @[Provides Singleton]
    fun provideTestApi(retrofit: Retrofit): TestApi = retrofit.create(TestApi::class.java)

    @[Provides Singleton]
    fun getGson(): Gson = GsonBuilder()
        .setLenient()
        .create()
}