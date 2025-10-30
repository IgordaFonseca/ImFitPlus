package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.os.Bundle
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

    }
}