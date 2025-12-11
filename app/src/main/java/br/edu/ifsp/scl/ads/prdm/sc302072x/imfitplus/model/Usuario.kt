package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model

class Usuario(
    val nome: String, var idade: Int?, val altura: Float?, val sexo: String, var peso: Float?,
    val nivelAtividade: String, var imc: Float?, var categoriaImc: String, var tmb: Double?, var pesoIdeal: Float
) {

    override fun toString(): String {
        return "Usuario(nome='$nome', idade=$idade, altura=$altura, sexo='$sexo', peso=$peso)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        return nome == other.nome
    }

    override fun hashCode(): Int {
        return nome.hashCode()
    }


}