package pe.santillan.rappi.data.rest.api

import okhttp3.OkHttpClient
import pe.santillan.rappi.data.rest.interceptor.NetworkInterceptor
import pe.santillan.rappi.data.rest.interceptor.QueryApiKeyInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceBuilder {
    inline fun <reified T> create(
        apiBaseUrl: String,
        apiKeyName: String = "api_key",
        apiKeyValue: String = "",
    ): T {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(NetworkInterceptor.httpLogging())
            .addInterceptor(QueryApiKeyInterceptor(apiKeyName, apiKeyValue))
            .build()

        return Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(T::class.java)
    }
}