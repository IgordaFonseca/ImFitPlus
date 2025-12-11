package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model

interface UsuarioDao {
    fun criarUsuario(usuario: Usuario): Long
    fun consultarUsuario(nome: String): Usuario
    fun consltuarCOntatos(): MutableList<Usuario>
    fun atuallizarUsuario(usuario: Usuario): Int
    fun deletarUsuario(nome: String): Int
}