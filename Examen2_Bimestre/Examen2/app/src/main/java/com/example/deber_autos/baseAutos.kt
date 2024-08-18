package com.example.deber_autos

class baseAutos (
    var idauto:Int,
    var nombreModelo:String,
    var precioModelo:Int,
    var tipomodelo:String,
    var carro: String
) {
    override fun toString(): String {
        return "${precioModelo}"
    }
}