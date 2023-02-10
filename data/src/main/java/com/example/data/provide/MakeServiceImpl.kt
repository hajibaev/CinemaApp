package com.example.data.provide

import com.example.data.cloud.server.Utils


class MakeServiceImpl(
    private val retrofitBuilder: ProvideRetrofitBuilder,
) : MakeService {

    private val retrofit by lazy(LazyThreadSafetyMode.NONE) {
        retrofitBuilder
            .provideRetrofitBuilder()
            .baseUrl(Utils.BASE_URL)
            .build()
    }

    override fun <T> service(clasz: Class<T>): T = retrofit.create(clasz)
}