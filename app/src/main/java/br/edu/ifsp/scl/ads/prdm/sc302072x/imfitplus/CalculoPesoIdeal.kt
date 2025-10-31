package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityCalculoPesoIdealBinding

class CalculoPesoIdeal : AppCompatActivity() {
    private lateinit var activityCalculoPesoIdealBinding: ActivityCalculoPesoIdealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityCalculoPesoIdealBinding = ActivityCalculoPesoIdealBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(activityCalculoPesoIdealBinding.root)

        val i = intent
        val nome = i.extras?.getString("nome")
        val altura  = i.extras?.getString("altura")?.toFloatOrNull()
        val sexo = i.extras?.getString("sexo")
        val nivelAtividade = i.extras?.getString("nivelAtividade")
        val peso = i.extras?.getString("peso")?.toFloatOrNull()
        val idade = i.extras?.getString("idade")?.toIntOrNull()
        val categoria = i.extras?.getString("categoria")
        val imc = i.extras?.getString("imc")
        val gastoCalorico = i.extras?.getString("gastoCalorico")
        var pesoIdeal: Float = 0.0f
        var difPeso: Float = 0.0f

        if (altura != null && altura >0 &&  peso != null && peso > 0){
            pesoIdeal = 22*(altura*altura)
            difPeso = peso - pesoIdeal
        }else{
            Toast.makeText(this, "Dados invalidos", Toast.LENGTH_SHORT).show()
        }
        activityCalculoPesoIdealBinding.pesoIdealTv.setText("Peso ideial: %.2f".format(pesoIdeal))
        activityCalculoPesoIdealBinding.diferencaPesoIdealTv.setText("Diferença de peso para peso ideal: %.2f".format(difPeso))

        activityCalculoPesoIdealBinding.resumoBt.setOnClickListener {
            val i = Intent(this, ResumoDaSaude::class.java)

            i.putExtra("nome", nome)
            i.putExtra("idade", idade.toString())
            i.putExtra("sexo", sexo)
            i.putExtra("nivelAtividade", nivelAtividade)
            i.putExtra("altura", altura.toString())
            i.putExtra("peso", peso.toString())
            i.putExtra("categoria", categoria)
            i.putExtra("imc", imc)
            i.putExtra("gastoCalorico", gastoCalorico)
            i.putExtra("pesoIdeal", pesoIdeal).toString()

            startActivity(i)
            finish()
        }

        activityCalculoPesoIdealBinding.voltarBt.setOnClickListener {
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