package com.example.deber_autos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteAutos (
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "Modelos",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador=
            """
               CREATE TABLE MODELOS(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombreModelo VARCHAR(50),
                precioModelo Integer,
                tipomodelo VARCHAR(50),
                carro VARCHAR(50)
               )

           """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}
    fun crearAutos(
        nombremodelo:String,
        preciomodelo: Int,
        tipomodelo:String,
        carro:String
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar= ContentValues()
        valoresAGuardar.put("nombre",nombremodelo)
        valoresAGuardar.put("precio",preciomodelo)
        valoresAGuardar.put("tipomodelo",tipomodelo)
        valoresAGuardar.put("carro",carro)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "COMPANIA", //nombre de la tabla
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarAutosFormulario(id:Int):Boolean{
        val conexionEscritura=writableDatabase
        val parametrosConsultaDelete= arrayOf(id.toString())
        val resultadoEliminacion=conexionEscritura
            .delete(
                "COMPANIA",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarAutosFormulario(
        nombremodelo:String, preciomodelo: Int, tipomodelo: String,carro: String, id: Int
    ):Boolean{
        val conexionEscritura= writableDatabase
        val valoresActualizar= ContentValues()
        valoresActualizar.put("nombre",nombremodelo)
        valoresActualizar.put("precio",preciomodelo)
        valoresActualizar.put("tipo",tipomodelo)
        valoresActualizar.put("carro",carro)

        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "MODELOS",
                valoresActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultaAutosPorID(id: Int):BAutos?{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura="""
            SELECT * FROM MODELOS WHERE ID = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura= arrayOf(
            id.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )
        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BAutos>()
        if(existeAlMenosUno){
            do{
                val entrenador = BAutos(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getInt(2),
                    resultadoConsultaLectura.getString(3),
                    resultadoConsultaLectura.getString(4),
                )
                arregloRespuesta.add(entrenador)
            }while(resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if(arregloRespuesta.size>0) arregloRespuesta[0] else null
    }
















    fun ejecutar(){
        eliminarAutosFormulario(1)
        actualizarAutosFormulario("dato",1,"h","h",1)
        consultaAutosPorID(2)
        crearAutos("dato",1,"h","h")
        SqliteAutos(contexto = null)
    }
}