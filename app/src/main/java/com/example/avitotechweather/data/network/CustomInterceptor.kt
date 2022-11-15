package com.example.avitotechweather.data.network
import com.example.avitotechweather.utils.Utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val url = chain.request().url.newBuilder()
			.addQueryParameter("appid", API_KEY)
			.build()
		return chain.proceed(chain.request().newBuilder().url(url)
			.build())
	}
}