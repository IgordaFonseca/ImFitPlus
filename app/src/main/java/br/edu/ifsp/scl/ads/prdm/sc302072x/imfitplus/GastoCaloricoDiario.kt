package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.icu.util.LocaleData
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityGastoCaloricoDiarioBinding
import java.time.LocalDate
import java.time.Period
import java.util.Date

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
        val dataNascimento = i.extras?.getString("dataNascimento")
        val imc = i.extras?.getString("imc")
        val categoria = i.extras?.getString("categoria")
        var tmb: Double = 0.0
        var constNivelAtividade: Double = 0.0
        val modoEdicao = intent.getBooleanExtra("modoEdicao", false)

        fun calcularIdade(dataNascimento: LocalDate): Int{
            val dataAtual = LocalDate.now()
            return Period.between(dataNascimento, dataAtual).years
        }

        val idade = calcularIdade(LocalDate.parse(dataNascimento))
        val fcmax = 220 - idade

        fun calcularZonaTreino (fcmax: Int): String{
            if(fcmax>=50 || fcmax<=60){
                return "Zona Leve"
            }else if(fcmax>=61 || fcmax<=70){
                return "Zona queima de gordura"
            }else if(fcmax>=71 || fcmax<=80){
                return "Zona aeróbica"
            }else{
                return "Zona anaeróbica"
            }
        }
        val zonaTreino = calcularZonaTreino(fcmax)

        if(sexo=="Masculino"){
            if(peso !=null && peso >0 && altura != null && altura > 0 && idade != null && idade > 0 ){
                tmb = 66+(13.7*peso) + (5*altura*100) - (6.8*idade)
            }else{
                Toast.makeText(this, "Dados invalidos", Toast.LENGTH_SHORT).show()
            }
        }else{
            if(peso !=null && peso >0 && altura != null && altura > 0 && idade != null && idade > 0 ){
                tmb = 655+(9.6*peso) + (1.8*altura*100) - (4.7*idade)
            }else{
                Toast.makeText(this, "Dados invalidos", Toast.LENGTH_SHORT).show()
            }
        }

        constNivelAtividade = when(nivelAtividade?.lowercase()){
            "sedentário"  ->  1.2
            "leve" -> 1.375
            "moderado" -> 1.55
            "intenso" -> 1.725
            else -> 1.2
        }

        val gastoCalorico = tmb * constNivelAtividade


        activityGastoCaloricoBinding.gastoCaloricoTv.setText(("TMB = %.2f, constante de nivel de atividade: %.3f, " +
                "gasto calórico diario: %.2f").format(tmb, constNivelAtividade, gastoCalorico))


        activityGastoCaloricoBinding.calcularPesoIdealBt.setOnClickListener {
            val i  = Intent(this, CalculoPesoIdeal::class.java)
            i.putExtra("nome", nome)
            i.putExtra("idade", dataNascimento.toString())
            i.putExtra("sexo", sexo)
            i.putExtra("nivelAtividade", nivelAtividade)
            i.putExtra("altura", altura.toString())
            i.putExtra("peso", peso.toString())
            i.putExtra("categoria", categoria)
            i.putExtra("imc", imc)
            i.putExtra("gastoCalorico", gastoCalorico.toString())
            i.putExtra("tmb", tmb.toString())
            i.putExtra("modoEdicao", modoEdicao)
            i.putExtra("dataNascimento", dataNascimento)
            i.putExtra("fcmax", fcmax)
            i.putExtra("zonaTreino",zonaTreino)

            startActivity(i)
            finish()
        }
        activityGastoCaloricoBinding.voltarBt.setOnClickListener {
            val iVoltar = Intent(this, ResultadoIMC::class.java)

            iVoltar.putExtra("nome", nome)
            iVoltar.putExtra("idade", dataNascimento.toString())
            iVoltar.putExtra("sexo", sexo)
            iVoltar.putExtra("nivelAtividade", nivelAtividade)
            iVoltar.putExtra("altura", altura.toString())
            iVoltar.putExtra("peso", peso.toString())
            iVoltar.putExtra("imc", imc.toString())
            iVoltar.putExtra("categoria", categoria.toString())
            iVoltar.putExtra("dataNascimento", dataNascimento)

            startActivity(iVoltar)
            finish()
        }

    }
}