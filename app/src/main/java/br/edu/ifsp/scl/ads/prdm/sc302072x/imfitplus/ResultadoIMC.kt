package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityResultadoImcBinding

class ResultadoIMC : AppCompatActivity() {
    private lateinit var activityResultadoImcBinding: ActivityResultadoImcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultadoImcBinding = ActivityResultadoImcBinding.inflate(layoutInflater)
        setContentView(activityResultadoImcBinding.root)
        val i = intent
        val nome = i.extras?.getString("nome")
        var idade = i.extras?.getString("idade")
        val sexo =  i.extras?.getString("sexo")
        val nivelAtividade = i.extras?.getString("nivelAtividade")
        val altura= i.extras?.getString("altura")?.toFloatOrNull()
        val peso= i.extras?.getString("peso")?.toFloatOrNull()
        val modoEdicao = intent.getBooleanExtra("modoEdicao", false)

        var imc: Float = 0.0F
        if (peso != null && peso > 0 &&  altura != null && altura > 0){
            imc = peso/(altura*altura)
        }

        var categoria: String = ""

        if(imc <18.5){
            categoria = "Abaixo do peso"
        }else if(imc <= 24.9){
            categoria = "Normal"
        }else if(imc <= 29.9){
            categoria = "Sobrepeso"
        }else{
            categoria = "Obesidade"
        }




        activityResultadoImcBinding.nomeTv.setText("Nome: ${nome}")
        activityResultadoImcBinding.imcTv.setText("IMC: %.2f".format(imc))
        activityResultadoImcBinding.categoriaTv.setText("Categoria: ${categoria}")


        activityResultadoImcBinding.calcularGastoBt.setOnClickListener {
            var i = Intent(this, GastoCaloricoDiario::class.java)

            i.putExtra("nome", nome)
            i.putExtra("idade", idade.toString())
            i.putExtra("sexo", sexo)
            i.putExtra("nivelAtividade", nivelAtividade)
            i.putExtra("altura", altura.toString())
            i.putExtra("peso", peso.toString())
            i.putExtra("imc",imc.toString())
            i.putExtra("categoria", categoria)
            i.putExtra("modoEdicao", modoEdicao)

            startActivity(i)
            finish()

        }

        activityResultadoImcBinding.voltarBt.setOnClickListener {
            var iVoltar = Intent(this, DadosPessoais:: class.java)
            startActivity(iVoltar)
            finish()
        }


    }
}