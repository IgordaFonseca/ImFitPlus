package br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.ads.prdm.sc302072x.imfitplus.R
import java.sql.SQLException

class UsuarioSqlite(context: Context) : UsuarioDao {

    companion object{
        private val USUARIO_DATABASE_FILE = "listaUsuarios"
        private val USUARIO_TABLE = "usuario"
        private val NOME_COLUMN = "nome"
        private val IDADE_COLUMN = "idade"
        private val ALTURA_COLUMN = "altura"
        private val SEXO_COLUMN = "sexo"
        private val PESO_COLUMN = "peso"
        private val NIVEL_ATIVIDADE_COLUMN = "nivelAtividade"
        private val IMC_COLUMN = "imc"
        private val CATEGORIA_IMC_COLUMN = "categoriaImc"
        private val TMB_COLUMN = "tmb"
        private val PESO_IDEAL_COLUMN = "pesoIdeal"
        private val DATA_NASCIMENTO_COLUMN = "dataNascimento"
        private val FCMAX_COLUMN = "fcmax"
        private val ZONA_TREINO_COLUMN = "zonaTreino"

        val CREATE_USUARIO_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $USUARIO_TABLE ( " +
                "$NOME_COLUMN TEXT NOT NULL PRIMARY KEY, " +
                "$IDADE_COLUMN INTEGER NOT NULL, " +
                "$ALTURA_COLUMN FLOAT NOT NULL, " +
                "$SEXO_COLUMN TEXT NOT NULL, " +
                "$PESO_COLUMN FLOAT NOT NULL, " +
                "$NIVEL_ATIVIDADE_COLUMN TEXT NOT NULL, " +
                "$IMC_COLUMN FLOAT NOT NULL, " +
                "$CATEGORIA_IMC_COLUMN TEXT NOT NULL, " +
                "$TMB_COLUMN DOUBLE NOT NULL, " +
                "$PESO_IDEAL_COLUMN FLOAT NOT NULL," +
                "$DATA_NASCIMENTO_COLUMN TEXT NOT NULL" +
                "$FCMAX_COLUMN TEXT NOT NULL" +
                "$ZONA_TREINO_COLUMN NOT NULL);"
    }

    //Criando uma instancia do SQLite
    val usuarioDataBase: SQLiteDatabase = context.openOrCreateDatabase(
        USUARIO_DATABASE_FILE,
        MODE_PRIVATE,
        null)


    // Criando a tabela de usuarios
    init {
        try {
            usuarioDataBase.execSQL(CREATE_USUARIO_TABLE_STATEMENT)
        }catch (se: SQLException){
            Log.e(context.getString(R.string.app_name), se.message.toString())
        }
    }

    override fun criarUsuario(usuario: Usuario): Long {
        return usuarioDataBase.insert(USUARIO_TABLE,null,usuario.toContentValues())
    }

    override fun consultarUsuario(nome: String): Usuario? {
        val cursor = usuarioDataBase.query( USUARIO_TABLE,null,  "$NOME_COLUMN = ?", arrayOf(nome), null, null, null, null)
       val usuarioEncontrado = if(cursor.moveToFirst()){
          cursor.toUsuario() // essa função subtitui o construtor do usuário pra não ter que ficar digitando o construtor de novo
       }else {
           null
       }
        cursor.close()
        return usuarioEncontrado
    }

    override fun consltuarUsuarios(): MutableList<Usuario> {
        val listaUsuarios : MutableList<Usuario> = mutableListOf()
        val cursor = usuarioDataBase.rawQuery("SELECT * FROM $USUARIO_TABLE;",null)

        while (cursor.moveToNext()){
           listaUsuarios.add(cursor.toUsuario())
        }
        return listaUsuarios
    }

    override fun atuallizarUsuario(usuario: Usuario): Int = usuarioDataBase.update(
        USUARIO_TABLE,
        usuario.toContentValues(),
        "$NOME_COLUMN = ?",
        arrayOf(usuario.nome)
    )

    override fun deletarUsuario(nome: String): Int = usuarioDataBase.delete(
        USUARIO_TABLE,
        "$NOME_COLUMN = ?",
        arrayOf(nome)
    )
    private fun Usuario.toContentValues() = ContentValues().apply {
        put(NOME_COLUMN, nome)
        put(IDADE_COLUMN, idade)
        put(ALTURA_COLUMN, altura)
        put(SEXO_COLUMN, sexo)
        put(PESO_COLUMN, peso)
        put(NIVEL_ATIVIDADE_COLUMN, nivelAtividade)
        put(IMC_COLUMN, imc)
        put(CATEGORIA_IMC_COLUMN, categoriaImc)
        put(TMB_COLUMN, tmb)
        put(PESO_IDEAL_COLUMN, pesoIdeal)
        put(DATA_NASCIMENTO_COLUMN, dataNascimento)
        put(FCMAX_COLUMN, fcmax)
        put(ZONA_TREINO_COLUMN, zonaTreino)
    }

    private fun Cursor.toUsuario(): Usuario {
        return Usuario(
            nome = getString(getColumnIndexOrThrow(NOME_COLUMN)),
            idade = getInt(getColumnIndexOrThrow(IDADE_COLUMN)),
            altura = getFloat(getColumnIndexOrThrow(ALTURA_COLUMN)).toFloat(),
            sexo = getString(getColumnIndexOrThrow(SEXO_COLUMN)),
            peso = getFloat(getColumnIndexOrThrow(PESO_COLUMN)).toFloat(),
            nivelAtividade = getString(getColumnIndexOrThrow(NIVEL_ATIVIDADE_COLUMN)),
            imc = getFloat(getColumnIndexOrThrow(IMC_COLUMN)).toFloat(),
            categoriaImc = getString(getColumnIndexOrThrow(CATEGORIA_IMC_COLUMN)),
            tmb = getDouble(getColumnIndexOrThrow(TMB_COLUMN)),
            pesoIdeal = getFloat(getColumnIndexOrThrow(PESO_IDEAL_COLUMN)).toFloat(),
            dataNascimento =  getString(getColumnIndexOrThrow(DATA_NASCIMENTO_COLUMN)),
            fcmax = getInt(getColumnIndexOrThrow(FCMAX_COLUMN).toInt()),
            zonaTreino = getString(getColumnIndexOrThrow(ZONA_TREINO_COLUMN))
        )
    }


}