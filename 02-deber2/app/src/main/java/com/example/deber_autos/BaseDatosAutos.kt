package com.example.deber_autos

class BaseDatosAutos {

    companion object{
        val arregloAutos = arrayListOf<baseAutos>()

        init {
            arregloAutos
                .add(baseAutos(1,"Corolla Sedan",1500, "Sedan", "Electrico"))
            arregloAutos
                .add(baseAutos(2,"NISSAN JSSS",1500, "JSS", "Electrico"))
            arregloAutos
                .add(baseAutos(3,"CHEVROLET HYYY",1500, "HYY", "Gasolina"))
        }

        fun actualizarAuto(id: Int, nombre: String, precioModel: Int, tipomodelo: String,tipocarro:String) {
            val compania = arregloAutos.find { it.idauto == id }
            compania?.let {
                it.nombreModelo = nombre
                it.precioModelo = precioModel
                it.tipomodelo= tipomodelo
                it.carro= tipocarro
            }
        }
    }
}