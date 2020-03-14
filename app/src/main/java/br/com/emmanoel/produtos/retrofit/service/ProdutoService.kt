package br.com.emmanoel.produtos.retrofit.service

import br.com.emmanoel.produtos.model.Produto
import retrofit2.Call
import retrofit2.http.GET

interface ProdutoService {

    @GET("produtos")
    fun list(): Call<List<Produto>>



}