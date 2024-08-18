package com.example.deber_autos

import android.content.ContentValues

class BaseCompania(
    var id:Int,
    var nombre:String,
    var descripcion:String?
){
    override fun toString(): String{
        return nombre
    }
}