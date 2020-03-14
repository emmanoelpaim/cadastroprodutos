package br.com.emmanoel.produtos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.emmanoel.produtos.model.Produto
import br.com.emmanoel.produtos.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = RetrofitInitializer().produtoService().list()
        call.enqueue(object : Callback<List<Produto>?> {
            override fun onResponse(call: Call<List<Produto>?>?,
                                    response: Response<List<Produto>?>?) {
                response?.body()?.let {
                    val produtos: List<Produto> = it
                    configureList(produtos)
                }
            }

            override fun onFailure(call: Call<List<Produto>?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun configureList(produtos: List<Produto>) {
        val recyclerView = findViewById<RecyclerView>(R.id.produto_list_recyclerview)
        recyclerView.adapter = ProdutoListAdapter(produtos, this)
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}