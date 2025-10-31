package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityCalculoPesoIdealBinding

class CalculoPesoIdeal : AppCompatActivity() {
    private lateinit var activityCalculoPesoIdealBinding: ActivityCalculoPesoIdealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityCalculoPesoIdealBinding = ActivityCalculoPesoIdealBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(activityCalculoPesoIdealBinding.root)

        val i = intent
        val altura = i.extras?.getString("altura")?.toFloatOrNull()
        val peso = i.extras?.getString("peso")?.toFloatOrNull()
        var pesoIdeal: Float = 0.0f
        var difPeso: Float = 0.0f

        if (altura != null && altura >0 &&  peso != null && peso > 0){
            pesoIdeal = 22*(altura*altura)
            difPeso = peso - pesoIdeal
        }else{
            Toast.makeText(this, "Dados invalidos", Toast.LENGTH_SHORT).show()
        }
        activityCalculoPesoIdealBinding.pesoIdealTv.setText("Peso ideial: %.2f".format(pesoIdeal))
        activityCalculoPesoIdealBinding.diferencaPesoIdealTv.setText("Diferença de peso para peso ideial: %.2f".format(difPeso))

        activityCalculoPesoIdealBinding.voltarBt.setOnClickListener {
            val i = Intent(this, GastoCaloricoDiario::class.java)
            startActivity(i)
            finish()
        }
    }
}