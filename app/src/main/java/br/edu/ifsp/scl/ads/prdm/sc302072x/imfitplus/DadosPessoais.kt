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
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityDadosPessoaisBinding

class DadosPessoais : AppCompatActivity() {
    private lateinit var activityDadosPessoaisBinding: ActivityDadosPessoaisBinding
    private var sexo: String = ""
    private var nivelAtividade: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDadosPessoaisBinding = ActivityDadosPessoaisBinding.inflate(layoutInflater)
        setContentView(activityDadosPessoaisBinding.root)
        activityDadosPessoaisBinding.nivelAtividadeSp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               nivelAtividade = resources.getStringArray(R.array.spiner_nivel_atividade_fisica)[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nivelAtividade =  ""
            }
        }

        activityDadosPessoaisBinding.calcularBt.setOnClickListener {
            var nome = activityDadosPessoaisBinding.nomeEt.text.toString()
            var idade = activityDadosPessoaisBinding.idadeEt.text.toString()
            val chekedId = activityDadosPessoaisBinding.sexoRg.checkedRadioButtonId
            if (chekedId!=-1){
                val radioButton = findViewById<RadioButton>(chekedId)
                sexo =  radioButton.text.toString()
            }
            var altura = activityDadosPessoaisBinding.alturaEt.text.toString()
            var peso = activityDadosPessoaisBinding.pesoEt.text.toString()

            if (nome.isEmpty()||idade.isEmpty()||sexo.isEmpty()||altura.isEmpty()||peso.isEmpty()||nivelAtividade.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var i = Intent (this, ResultadoIMC :: class.java)
            i.putExtra("nome", nome)
            i.putExtra("idade", idade.toString())
            i.putExtra("altura", altura.toString())
            i.putExtra("peso", peso.toString())
            i.putExtra("nivelAtividade", nivelAtividade)
            i.putExtra("sexo", sexo)
            startActivity(i)
            finish()
            }

        }
}