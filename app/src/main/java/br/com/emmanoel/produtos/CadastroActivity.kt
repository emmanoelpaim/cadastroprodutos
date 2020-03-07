package br.com.emmanoel.produtos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.com.emmanoel.produtos.model.Produto

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    fun cadastrarProduto(view: View?) {
        val textoPrecoCadastro = findViewById(R.id.textoPrecoCadastro) as EditText
        val textoNomeCadastro = findViewById(R.id.textoNomeCadastro) as EditText

        val preco = textoPrecoCadastro.text.toString()
        val nome = textoNomeCadastro.text.toString()

        val produto = Produto(preco, nome)

        val it = Intent().apply {
            putExtra("produto", produto)
        }
        setResult(Activity.RESULT_OK, it)

        finish()
    }

    fun cancelarCadastro(view: View?) {
        finish()
    }
}
