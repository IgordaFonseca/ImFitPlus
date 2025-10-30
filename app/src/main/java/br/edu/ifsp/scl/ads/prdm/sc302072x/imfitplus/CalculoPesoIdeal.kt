package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.os.Bundle
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
    }
}