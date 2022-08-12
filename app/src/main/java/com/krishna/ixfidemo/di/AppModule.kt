package com.krishna.ixfidemo.di

import android.content.Context
import com.krishna.ixfidemo.BuildConfig
import com.krishna.ixfidemo.data.network.ApiServices
import com.krishna.ixfidemo.data.network.FlowStreamAdapter
import com.krishna.ixfidemo.data.network.WebSocketService
import com.squareup.moshi.Moshi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun providesUrl() = "https://www.binance.com/"

    @Provides
    @Singleton
    fun apiService(): ApiServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(providesUrl())
            .build()
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    fun provideScarlet(application: BaseApp, client: OkHttpClient, moshi: Moshi): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(client.newWebSocketFactory("wss://stream.binance.com:9443"))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory(moshi))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }


    @Provides
    fun providesApplication(@ApplicationContext context: Context): BaseApp {
        return context as BaseApp
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }


    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
                else HttpLoggingInterceptor.Level.NONE
            )

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    fun provideSocketService(scarlet: Scarlet): WebSocketService {
        return scarlet.create()
    }
}