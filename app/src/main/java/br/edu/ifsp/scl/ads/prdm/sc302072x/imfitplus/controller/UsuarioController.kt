package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.controller

import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.ResumoDaSaude
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model.Usuario
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model.UsuarioDao
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model.UsuarioSqlite

/* No exemplo da aula o professor sugeriu utilizar o nome da activty, mas o ChatGpt sugeriu utilizar o context pois seria um elemento genérico
que poderia ser utilizado em qualquer view, não somente na veiw passada como parametro */
class UsuarioController(private val context: Context) {
    private val usuarioDao: UsuarioDao = UsuarioSqlite(context)

    fun inserirUsuario(usuario: Usuario) = usuarioDao.criarUsuario(usuario)
    fun getUsuario(nome: String): Usuario? = usuarioDao.consultarUsuario(nome)
    fun getUsuarios() =  usuarioDao.consltuarUsuarios()
    fun modificarUsuario(usuario: Usuario) = usuarioDao.atuallizarUsuario(usuario)
    fun removerUsuario(nome: String) = usuarioDao.deletarUsuario(nome)
}