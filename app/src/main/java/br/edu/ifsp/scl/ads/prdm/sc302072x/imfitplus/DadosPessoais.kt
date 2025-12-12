package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.controller.UsuarioController
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityDadosPessoaisBinding

class DadosPessoais : AppCompatActivity() {
    private lateinit var activityDadosPessoaisBinding: ActivityDadosPessoaisBinding
    private var sexo: String = ""
    private var nivelAtividade: String = ""

    private val usuarioController: UsuarioController by lazy {
        UsuarioController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDadosPessoaisBinding = ActivityDadosPessoaisBinding.inflate(layoutInflater)
        setContentView(activityDadosPessoaisBinding.root)

        // Spinner: listener
        activityDadosPessoaisBinding.nivelAtividadeSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    nivelAtividade =
                        resources.getStringArray(R.array.spiner_nivel_atividade_fisica)[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    nivelAtividade = ""
                }
            }

        val modoEdicao = intent.getBooleanExtra("modoEdicao", false)
        if (modoEdicao) {
            val nomeEdicao = intent.getStringExtra("nome") ?: ""
            val usuario = usuarioController.getUsuario(nomeEdicao)

            if (usuario != null) {
                activityDadosPessoaisBinding.nomeEt.setText(usuario.nome)
                activityDadosPessoaisBinding.idadeEt.setText(usuario.idade?.toString() ?: "")
                activityDadosPessoaisBinding.alturaEt.setText(usuario.altura?.toString() ?: "")
                activityDadosPessoaisBinding.pesoEt.setText(usuario.peso?.toString() ?: "")

                for (i in 0 until activityDadosPessoaisBinding.sexoRg.childCount) {
                    val view = activityDadosPessoaisBinding.sexoRg.getChildAt(i)

                    if (view is RadioButton) {
                        if (view.text.toString() == usuario.sexo) {
                            view.isChecked = true
                            sexo = usuario.sexo
                            break
                        }
                    }
                }

                val niveis = resources.getStringArray(R.array.spiner_nivel_atividade_fisica)
                val indice = niveis.indexOf(usuario.nivelAtividade)
                if (indice >= 0) {
                    activityDadosPessoaisBinding.nivelAtividadeSp.setSelection(indice)
                    nivelAtividade = usuario.nivelAtividade
                }

                // Nome é PK: não pode editar
                activityDadosPessoaisBinding.nomeEt.isEnabled = false
            }
        }

        activityDadosPessoaisBinding.calcularBt.setOnClickListener {
            val nome = activityDadosPessoaisBinding.nomeEt.text.toString()
            val idade = activityDadosPessoaisBinding.idadeEt.text.toString()
            val chekedId = activityDadosPessoaisBinding.sexoRg.checkedRadioButtonId
            if (chekedId != -1) {
                val radioButton = findViewById<RadioButton>(chekedId)
                sexo = radioButton.text.toString()
            }
            val altura = activityDadosPessoaisBinding.alturaEt.text.toString()
            val peso = activityDadosPessoaisBinding.pesoEt.text.toString()

            if (nome.isEmpty() || idade.isEmpty() || sexo.isEmpty() ||
                altura.isEmpty() || peso.isEmpty() || nivelAtividade.isEmpty()
            ) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

           /*

           criar o botão no xml

           activityDadosPessoaisBinding.deletarBt.visibility =
                if (modoEdicao) View.VISIBLE else View.GONE

            if (modoEdicao) {
                val nomeEdicao = intent.getStringExtra("nome") ?: ""

                activityDadosPessoaisBinding.deletarBt.setOnClickListener {
                    usuarioController.removerUsuario(nomeEdicao)
                    finish() // volta para a lista
                }
            }*/

            val i = Intent(this, ResultadoIMC::class.java)
            i.putExtra("nome", nome)
            i.putExtra("idade", idade)
            i.putExtra("altura", altura)
            i.putExtra("peso", peso)
            i.putExtra("nivelAtividade", nivelAtividade)
            i.putExtra("sexo", sexo)
            i.putExtra("modoEdicao", modoEdicao)

            startActivity(i)
            finish()
        }
    }
}