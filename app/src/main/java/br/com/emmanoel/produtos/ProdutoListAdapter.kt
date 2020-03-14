package br.com.emmanoel.produtos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.emmanoel.produtos.model.Produto
import kotlinx.android.synthetic.main.item_produto.view.*

class ProdutoListAdapter(private val produtos: List<Produto>,
                         private val context: Context
) : RecyclerView.Adapter<ProdutoListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder?.let {
            it.bindView(produto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(produto: Produto) {
            val nome = itemView.produto_nome
            val preco = itemView.produto_preco

            nome.text = produto.nome
            preco.text = produto.preco
        }

    }

}