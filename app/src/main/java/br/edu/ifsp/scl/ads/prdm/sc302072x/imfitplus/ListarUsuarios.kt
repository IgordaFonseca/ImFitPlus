package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.controller.UsuarioController
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.databinding.ActivityListarUsuariosBinding
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model.Usuario

class ListarUsuarios : AppCompatActivity() {
    private lateinit var listarUsuariosBinding: ActivityListarUsuariosBinding
    private lateinit var usuarioController: UsuarioController
    private lateinit var usuariosAdapter: ArrayAdapter<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listarUsuariosBinding = ActivityListarUsuariosBinding.inflate(layoutInflater)
        setContentView(listarUsuariosBinding.root)
        usuarioController = UsuarioController(this)
        val usuarios: MutableList<Usuario> = usuarioController.getUsuarios()

        //listarUsuariosBinding.usuariosLv.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usuarios)
        usuariosAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            usuarios
        )
        listarUsuariosBinding.usuariosLv.adapter = usuariosAdapter


        listarUsuariosBinding.voltarBt.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        listarUsuariosBinding.usuariosLv.setOnItemClickListener { _, _, position, _ ->
            val usuarioSelecionado = usuariosAdapter.getItem(position) ?: return@setOnItemClickListener

            val i = Intent(this, DadosPessoais::class.java)
            i.putExtra("modoEdicao", true)
            i.putExtra("nome", usuarioSelecionado.nome)

            startActivity(i)
        }


    }
    override fun onResume() {
        super.onResume()

        // Sempre que a Activity voltar a ficar visível, recarrega a lista
        val usuariosAtualizados = usuarioController.getUsuarios()
        usuariosAdapter.clear()
        usuariosAdapter.addAll(usuariosAtualizados)
        usuariosAdapter.notifyDataSetChanged()
    }
}