package pe.santillan.rappi.data.rest.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import pe.santillan.rappi.data.util.logJson

class QueryApiKeyInterceptor(private val name: String, private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(name, apiKey).build()
        return chain.proceed(request.newBuilder().url(url).build().also {
            logJson(it)
        })

    }
}