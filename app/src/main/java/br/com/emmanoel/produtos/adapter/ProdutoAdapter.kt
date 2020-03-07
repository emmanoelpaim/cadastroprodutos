package br.com.emmanoel.produtos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.emmanoel.produtos.R
import br.com.emmanoel.produtos.model.Produto

class ProdutoAdapter (private var listaProdutos:ArrayList<Produto>) : RecyclerView.Adapter<ProdutoAdapter.MyViewHolder>() {
    var onItemClickListener: OnItemClickListener? = null

    class MyViewHolder : RecyclerView.ViewHolder {
        var textPreco: TextView
        var textNome: TextView
        constructor(view: View) : super(view) {
            textPreco = view.findViewById(R.id.textoPreco)
            textNome = view.findViewById(R.id.textoNome)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProdutoAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_produto, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textPreco.text = listaProdutos.get(position).preco
        holder.textNome.text = listaProdutos.get(position).nome
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(
                holder.itemView,
                position
            )
        }
    }

    override fun getItemCount() = listaProdutos.size

    interface OnItemClickListener {
        fun onItemClicked(view: View, position: Int)
    }
}