package com.example.a2024accgr1galg

class BBaseDatosMemoria {
    companion object{
        val arregloEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloEntrenador
                .add(
                    BEntrenador(1,"Adrian","b@g")
                )
            arregloEntrenador
                .add(
                    BEntrenador(2,"Vicente", "d@g")
                )
            arregloEntrenador
                .add(
                    BEntrenador(3,"Carolina","t@e")
                )
        }
    }
}