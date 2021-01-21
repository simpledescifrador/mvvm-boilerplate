package com.esys.mvvmboilerplate.data.api

import com.esys.mvvmboilerplate.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val apiService: ApiService by lazy {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        val gson = gsonBuilder.create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build().create(ApiService::class.java)
    }
}
