package br.com.emmanoel.produtos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.com.emmanoel.produtos.model.Produto

class DetalheActivity : AppCompatActivity() {

    companion object {

        const val RESULT_EDIT = 1
        const val RESULT_DELETE = 2
    }

    private lateinit var textoPrecoDetalhe: EditText
    private lateinit var textoNomeDetalhe: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)
        val intent = intent
        val produto = intent.getSerializableExtra("produto") as Produto

        textoPrecoDetalhe = findViewById<EditText>(R.id.textoPrecoDetalhe).apply {
            setText(produto.preco)
        }
        textoNomeDetalhe = findViewById<EditText>(R.id.textoNomeDetalhe).apply {
            setText(produto.nome)
        }


    }

    fun editarProduto(v: View?) {
        val produto = Produto(
            textoPrecoDetalhe.text.toString(),
            textoNomeDetalhe.text.toString()
        )
        val data = Intent()
        data.putExtra("produto", produto)
        setResult(RESULT_EDIT, data)
        finish()
    }

    fun excluirProduto(v: View?) {
        setResult(RESULT_DELETE)
        finish()
    }

    fun voltar(v: View?) {
        finish()
    }
}
