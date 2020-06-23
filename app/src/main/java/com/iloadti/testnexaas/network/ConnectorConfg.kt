package com.iloadti.testnexaas.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//TLS
private const val TLS = "TLSv1.2"

//CONST HEADERS
private const val CONTENT_TYPE = "Content-Type"
private const val CONTENT_ENCODING = "Content-Encoding"
private const val CONTENT_XML_CHAR_UTF8 = "text/xml; charset=utf-8"
private const val CONTENT_UTF8 = "UTF-8"
private const val CONTENT_JSON = "application/json"

//TIME CONNECTION
private const val DEFAULT_TIMEOUT = 15000
private const val READ_TIME_OUT = 30000

//LOCAL/PORT
const val host: String = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/"

private fun createNetworkClient(): OkHttpClient {

    val interceptorLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
//                .removeHeader(CONTENT_TYPE)
//                .addHeader(CONTENT_TYPE, CONTENT_XML_CHAR_UTF8)
                .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .addInterceptor(interceptorLogging)
        .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
//        .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS))
        .build()
}


fun retrofitConfig(baseUrl: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(createNetworkClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
