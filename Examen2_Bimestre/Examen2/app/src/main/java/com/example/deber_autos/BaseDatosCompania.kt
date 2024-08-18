package com.example.deber_autos

class BaseDatosCompania {
    companion object{
        val arrgloBaseCompania = arrayListOf<BaseCompania>()

        init {
            arrgloBaseCompania
                .add(BaseCompania(1785,"TOYOTA","Camioneta"))
            arrgloBaseCompania
                .add(BaseCompania(2020,"NISSAN","Trailer"))
            arrgloBaseCompania
                .add(BaseCompania(1950,"CHEVROLET","Carro"))
        }

        fun actualizarCompania(id: Int, nombre: String, descripcion: String?) {
            val compania = arrgloBaseCompania.find { it.id == id }
            compania?.let {
                it.nombre = nombre
                it.descripcion = descripcion
            }
        }
    }
}