package br.com.emmanoel.produtos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.emmanoel.produtos.adapter.ProdutoAdapter
import br.com.emmanoel.produtos.model.Produto

class MainActivity : AppCompatActivity() , ProdutoAdapter.OnItemClickListener {
    private val REQ_CADASTRO = 1;
    private val REQ_DETALHE  = 2;
    private var listaProdutos: ArrayList<Produto> = ArrayList()
    private var posicaoAlterar=-1

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ProdutoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ProdutoAdapter(listaProdutos)
        viewAdapter.onItemClickListener = this


        listaProdutos.add(Produto("001","Prod1"))
        listaProdutos.add(Produto("002","Prod2"))

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {

            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter



        }
    }

    override fun onItemClicked(view: View, position: Int) {
        val it = Intent(this, DetalheActivity::class.java)
        this.posicaoAlterar = position
        val produto = listaProdutos.get(position)
        it.putExtra("produto", produto)
        startActivityForResult(it, REQ_DETALHE)
    }

    fun abrirFormulario(view: View) {
        val it = Intent(this, CadastroActivity::class.java)
        startActivityForResult(it, REQ_CADASTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CADASTRO) {
            if (resultCode == Activity.RESULT_OK) {
                val produto = data?.getSerializableExtra("produto") as Produto
                listaProdutos.add(produto)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                    .show()
            }
        } else if (requestCode == REQ_DETALHE) {
            if (resultCode == DetalheActivity.RESULT_EDIT) {
                val produto = data?.getSerializableExtra("produto") as Produto
                listaProdutos.set(this.posicaoAlterar, produto)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Edicao realizada com sucesso!", Toast.LENGTH_SHORT)
                    .show()
            } else if (resultCode == DetalheActivity.RESULT_DELETE) {
                listaProdutos.removeAt(this.posicaoAlterar)
                viewAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Exclusao realizada com sucesso!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
