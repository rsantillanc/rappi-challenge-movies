package pe.santillan.rappi.data.rest.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class QueryApiKeyInterceptor(private val name: String, private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(with(chain.request().apply {
            url.newBuilder().addQueryParameter(name, apiKey)
        }.newBuilder()) {
            build()
        })
    }
}