package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model

class Usuario(
    val nome: String, var idade: Int?, val altura: Float?, val sexo: String, var peso: Float?,
    val nivelAtividade: String, var imc: Float?, var categoriaImc: String, var tmb: Double?, var pesoIdeal: Float
) {



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        return nome == other.nome
    }

    override fun hashCode(): Int {
        return nome.hashCode()
    }

    override fun toString(): String {
        val idadeStr = idade?.toString() ?: "-"
        val alturaStr = altura.fmt2()
        val pesoStr = peso.fmt2()
        val imcStr = imc.fmt2()
        val tmbStr = tmb.fmt2()
        val pesoIdealStr = "%.2f".format(pesoIdeal)

        return "Usuário: $nome\n" +
                "Idade: $idadeStr | Sexo: $sexo\n" +
                "Altura: $alturaStr m | Peso: $pesoStr kg\n" +
                "IMC: $imcStr ($categoriaImc)\n" +
                "TMB: $tmbStr kcal/dia | Peso ideal: $pesoIdealStr kg"
    }
    private fun Float?.fmt2(): String =
        this?.let { "%.2f".format(it) } ?: "-"

    private fun Double?.fmt2(): String =
        this?.let { "%.2f".format(it) } ?: "-"



}