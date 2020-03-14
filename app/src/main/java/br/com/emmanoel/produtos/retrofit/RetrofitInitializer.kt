package br.com.emmanoel.produtos.retrofit

import br.com.emmanoel.produtos.retrofit.service.ProdutoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun produtoService() = retrofit.create(ProdutoService::class.java)
}