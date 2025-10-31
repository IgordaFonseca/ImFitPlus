package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityGastoCaloricoDiarioBinding

class GastoCaloricoDiario : AppCompatActivity() {
    private lateinit var activityGastoCaloricoBinding: ActivityGastoCaloricoDiarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityGastoCaloricoBinding = ActivityGastoCaloricoDiarioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityGastoCaloricoBinding.root)

        val i = intent
        val nome = i.extras?.getString("nome")
        val altura  = i.extras?.getString("altura")?.toFloatOrNull()
        val sexo = i.extras?.getString("sexo")
        val nivelAtividade = i.extras?.getString("nivelAtividade")
        val peso = i.extras?.getString("peso")?.toFloatOrNull()
        val idade = i.extras?.getString("idade")?.toIntOrNull()
        var tbm: Double = 0.0

        if(sexo=="Masculino"){
            if(peso !=null && peso >0 && altura != null && altura > 0 && idade != null && idade > 0 ){
                tbm = 66+(13.5*peso) + (5*altura*100) - (6.8*idade)
            }else{
                Toast.makeText(this, "Dados invalidos", Toast.LENGTH_SHORT).show()
            }
        }else{
            if(peso !=null && peso >0 && altura != null && altura > 0 && idade != null && idade > 0 ){
                tbm = 655+(9.6*peso) + (1.8*altura*100) - (4.7*idade)
            }else{
                Toast.makeText(this, "Dados invalidos", Toast.LENGTH_SHORT).show()
            }
        }

        activityGastoCaloricoBinding.gastoCaloricoTv.setText("TBM = %.2f".format(tbm))

        activityGastoCaloricoBinding.calcularPesoIdealBt.setOnClickListener {
            val i  = Intent(this, CalculoPesoIdeal::class.java)
            i.putExtra("nome", nome)
            i.putExtra("idade", idade.toString())
            i.putExtra("sexo", sexo)
            i.putExtra("nivelAtividade", nivelAtividade)
            i.putExtra("altura", altura.toString())
            i.putExtra("peso", peso.toString())

            startActivity(i)
            finish()
        }
        activityGastoCaloricoBinding.voltarBt.setOnClickListener {
            val iVoltar = Intent(this, ResultadoIMC::class.java)

            iVoltar.putExtra("nome", nome)
            iVoltar.putExtra("idade", idade.toString())
            iVoltar.putExtra("sexo", sexo)
            iVoltar.putExtra("nivelAtividade", nivelAtividade)
            iVoltar.putExtra("altura", altura.toString())
            iVoltar.putExtra("peso", peso.toString())

            startActivity(iVoltar)
            finish()
        }

    }
}