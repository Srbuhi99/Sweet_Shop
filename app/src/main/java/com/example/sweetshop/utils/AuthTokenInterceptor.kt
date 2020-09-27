package com.example.sweetshop.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization","Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWdvLmFyYWtlbHlhbkBnbWFpbC5jb20iLCJzY29wZXMiOiJST0" +
                    "xFX1VTRVIiLCJpYXQiOjE1OTg1MDk1NjksImV4cCI6MTYzMDA0NTU2OX0.7OMD_zp2FkMOQpG9IshJ4gC1F" +
                    "gChi2uqWYXU74yPjDk")
            .header("languageName","en")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}