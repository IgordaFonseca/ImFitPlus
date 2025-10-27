package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityDadosPessoaisBinding

class DadosPessoais : AppCompatActivity() {
    private lateinit var activityDadosPessoaisBinding: ActivityDadosPessoaisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDadosPessoaisBinding = ActivityDadosPessoaisBinding.inflate(layoutInflater)
        setContentView(activityDadosPessoaisBinding.root)

    }
}