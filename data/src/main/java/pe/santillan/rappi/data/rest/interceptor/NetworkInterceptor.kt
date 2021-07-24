package pe.santillan.rappi.data.rest.interceptor

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import pe.santillan.rappi.data.BuildConfig

object NetworkInterceptor {
    fun httpLogging(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}