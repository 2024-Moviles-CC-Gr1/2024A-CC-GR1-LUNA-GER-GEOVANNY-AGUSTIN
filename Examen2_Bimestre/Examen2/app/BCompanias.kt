package com.example.deber_autos

class BCompanias(
    var id:Int,
    var nombre:String,
    var descripcion:String?
){
    override fun toString(): String{
        return "$nombre ${descripcion}"
    }
}