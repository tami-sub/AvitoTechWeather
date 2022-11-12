package com.example.avitotechweather.data.network
import com.example.avitotechweather.data.network.Utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val url = chain.request().url.newBuilder()
			.addQueryParameter("appid", API_KEY)
			.build()
		val request = chain.request().newBuilder()
			// .addHeader("Authorization", "Bearer token")
			.url(url)
			.build()
		return chain.proceed(request)
	}
}