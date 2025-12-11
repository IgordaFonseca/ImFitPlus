package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.controller.UsuarioController
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityResumoDaSaudeBinding
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model.Usuario

class ResumoDaSaude : AppCompatActivity() {

    private lateinit var binding: ActivityResumoDaSaudeBinding

    private val usuarioController: UsuarioController by lazy {
        UsuarioController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResumoDaSaudeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        val nome = extras?.getString("nome") ?: ""
        val sexo = extras?.getString("sexo") ?: ""
        val nivelAtividade = extras?.getString("nivelAtividade") ?: ""
        val altura = extras?.getString("altura")?.toFloatOrNull()
        val peso = extras?.getString("peso")?.toFloatOrNull()
        val idade = extras?.getString("idade")?.toIntOrNull()
        val categoria = extras?.getString("categoria") ?: ""
        val tmb: Double? = extras?.getString("tmb")?.toDoubleOrNull()


        val imc = extras?.getString("imc")?.toFloatOrNull()
        //val gastoCalorico = extras?.getDouble("gastoCalorico", 0.0) ?: 0.0

        val gastoCaloricoStr = extras?.getString("gastoCalorico")
        val gastoCaloricoFromString = gastoCaloricoStr
            ?.replace(",", ".")
            ?.toDoubleOrNull()

        val gastoCaloricoFromDouble =
            if (extras != null && extras.containsKey("gastoCalorico")) {
                try { extras.getDouble("gastoCalorico") } catch (e: Exception) { 0.0 }
            } else {
                0.0
            }

        val gastoCalorico = gastoCaloricoFromString ?: gastoCaloricoFromDouble


        val pesoIdeal = extras?.getFloat("pesoIdeal", 0f) ?: 0f
        var ingestaoAgua: Double? = null
        if (peso != null && peso > 0f) {
            ingestaoAgua = (peso * 0.350f).toDouble()
        }

        binding.nomeTv.text = "Nome: $nome"
        binding.imcTv.text = if (imc != null) {
            "IMC: %.2f".format(imc)
        } else {
            "IMC: -"
        }
        binding.imcCategoriaTv.text = "Categoria do IMC: $categoria"
        binding.pesoIdealTv.text = "Peso ideal: %.2f".format(pesoIdeal)
        binding.gastoCaloricoTv.text = "Gasto calórico diário: %.2f".format(gastoCalorico)

        binding.recomendacaoIngestaoAguaTv.text = if (ingestaoAgua != null) {
            "Recomendação de ingestão de água: %.2f L".format(ingestaoAgua)
        } else {
            "Recomendação de ingestão de água: -"
        }

        val usuario = Usuario(nome, idade, altura, sexo, peso, nivelAtividade, imc, categoria, tmb, pesoIdeal)

        //log para teste
        Log.d("ResumoDaSaude", "Categoria recebida no intent = '$categoria'")

        usuarioController.inserirUsuario(usuario)

        binding.voltarBt.setOnClickListener {
            val iVolta = Intent(this, CalculoPesoIdeal::class.java)

            iVolta.putExtra("nome", nome)
            iVolta.putExtra("idade", idade?.toString() ?: "")
            iVolta.putExtra("sexo", sexo)
            iVolta.putExtra("nivelAtividade", nivelAtividade)
            iVolta.putExtra("altura", altura?.toString() ?: "")
            iVolta.putExtra("peso", peso?.toString() ?: "")
            iVolta.putExtra("categoria", categoria)
            iVolta.putExtra("imc", imc?.toString() ?: "")
            iVolta.putExtra("gastoCalorico", gastoCalorico)
            iVolta.putExtra("pesoIdeal", pesoIdeal)


            startActivity(iVolta)
            finish()
        }
    }
}
