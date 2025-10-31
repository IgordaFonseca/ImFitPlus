package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityResumoDaSaudeBinding

class ResumoDaSaude : AppCompatActivity() {
    private lateinit var activityResumoSaudeViewBinding: ActivityResumoDaSaudeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityResumoSaudeViewBinding = ActivityResumoDaSaudeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(activityResumoSaudeViewBinding.root)

        val i = intent
        val nome = i.extras?.getString("nome")
        val altura  = i.extras?.getString("altura")?.toFloatOrNull()
        val sexo = i.extras?.getString("sexo")
        val nivelAtividade = i.extras?.getString("nivelAtividade")
        val peso = i.extras?.getString("peso")?.toFloatOrNull()
        val idade = i.extras?.getString("idade")?.toIntOrNull()
        val categoria = i.extras?.getString("categoria")
        val imc = i.extras?.getString("imc")?.toIntOrNull()
        val gastoCalorico = i.extras?.getString("gastoCalorico")?.toDoubleOrNull()
        val pesoIdeal = i.extras?.getString("pesoIdeal")?.toFloatOrNull()
        var ingestaoAgua: Double = 0.0

        if(imc != null && imc>0 && pesoIdeal != null && pesoIdeal >0 && gastoCalorico != null && gastoCalorico > 0 && peso != null && peso>0){

            ingestaoAgua = peso * 0.350
            activityResumoSaudeViewBinding.nomeTv.setText(nome)
            activityResumoSaudeViewBinding.imcTv.setText("imc: %.2f".format(imc))
            activityResumoSaudeViewBinding.imcCategoriaTv.setText("Categoria do IMC: ${categoria}")
            activityResumoSaudeViewBinding.pesoIdealTv.setText("Peso ideal: %.2f".format(pesoIdeal))
            activityResumoSaudeViewBinding.gastoCaloricoTv.setText("Gasto calorico: %.2f".format(gastoCalorico))
            activityResumoSaudeViewBinding.recomendacaoIngestaoAguaTv.setText("Recomendaçao de agua %.2f Lts".format(ingestaoAgua))

        }

        activityResumoSaudeViewBinding.voltarBt.setOnClickListener {
            val iVolta = Intent(this, GastoCaloricoDiario::class.java)

            iVolta.putExtra("nome", nome)
            iVolta.putExtra("idade", idade.toString())
            iVolta.putExtra("sexo", sexo)
            iVolta.putExtra("nivelAtividade", nivelAtividade)
            iVolta.putExtra("altura", altura.toString())
            iVolta.putExtra("peso", peso.toString())
            i.putExtra("categoria", categoria)
            i.putExtra("imc", imc)
            i.putExtra("gastoCalorico", gastoCalorico)
            startActivity(iVolta)
            finish()
        }
    }
}