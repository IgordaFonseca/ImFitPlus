package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainbiding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainbiding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainbiding.root)

        activityMainbiding.comeceAquiBt.setOnClickListener {
            startActivity(Intent(this, DadosPessoais::class.java))
            finish()
        }


    }
}