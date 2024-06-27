import java.util.*

fun main() {
  println("Hola Mundo")

// INMUTABLES (No se Re Asigna "="), matrices no cambian pero si su interior
val inmutable: String = "Adrian"
// inmutable = "vicente" //error!
var mutable: String = "Vicente"
  mutable="Adrian" //ok
  //Val > Var

  // Duck Typing
 val ejemploVariable = "Adrian Eguez"
 val edadEjemplo: Int =12
 ejemploVariable.trim()
 //ejemploVariable=edadEjemplo //Error!

 //variables Primitivas
 val nombreProfesor: String = "Adrian Eguez"
 val sueldo: Double = 1.2
 val estadoCivil: Char ='C'
 val mayorEdad: Boolean= true

//Clases en Java
 val fechaNacimiento: Date = Date()

//when (switch)
val estadoCivilWhen = "c"
when (estadoCivilWhen){
    ("c") ->{
        println("casado")
    }
     "S" -> {
         println("Soltero")
    }
    else ->{
        println("No sabemos")
    }
  }
 val esSoltero = (estadoCivilWhen=="S")
 val coqueteo = if (esSoltero) "Si " else "No"  //if else chiquito

 calcularSueldo(10.00)
 calcularSueldo(10.00,15.00,20.00)

 //named parameters
    // calcularSueldo(sueldo, rtasa, bono especial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    val sumaUno = Suma(1,1)//new Suma (1,1) en KOTLIN no hay "new"
    val sumados = Suma(null,1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumados.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglos
    //Estaticos
    val arrefloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arrefloEstatico);

    //dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )

    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //para mejor rendimiento for o while
    //For EACH => Unit
    //Iterrar un arreglo
    val respuestaFroEach: Unit = arregloDinamico
        .forEach{ valorActual:Int ->//->=>
            println("Valor actual: ${valorActual}")
        }
    // "it" (en ingles "eso") significa el elemento iterado
    arregloDinamico.forEach{ println("valor Actual:${it}")}

    //Map -> Muta(modefica cambia) el arreglo
    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve un nuevo arreglo con valores
    // de las iteraciones

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map{it +15}
    // val respuestaMapDos = arregloDinamico.map{it.toDoble +15}
    println(respuestaMapDos)


    //filter => filtrar el arreglo
    // 1) devolver una expresion (tru o false)
    // 2) nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual:Int ->
            //expresion o condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter{it <= 5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    //Or And
    //OR -> ANY(alguno cumple?)
    //AND -> ALL (todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual: Int ->
            return@any (valorActual > 5)
        }

    println(respuestaAny) //True
    val respuestaAll: Boolean = arregloDinamico
        .all{ valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false

    // REDUCE -> valor actual
    //valor acumulado =  (siempre empieza en  en kotlin)
    //[1,2,3,4,5] -> Acumular "Sumar" estos valores del arreglo
    // valorIteracion1 =valorEmpieza +1 =0+1 = 1 -> iteracion1
    // valorIteracion2 =valorAcumuladoIteracion1 + 2 = 1+2 = 3 -> iteracion2
    // valorIteracion3 =valorAcumuladoIteracion2 + 3 = 3+3 = 6 -> iteracion3
    // valorIteracion4 =valorAcumuladoIteracion3 + 4 = 6+4 = 10 -> iteracio4
    // valorIteracion5 =valorAcumuladoIteracion4 + 5 = 10+5 = 15 -> iteracion5

    val respuestaReduce: Int = arregloDinamico
        .reduce{acumulado:Int, valorActual:Int ->
            return@reduce (acumulado + valorActual) // -> combiar a usar la logica de negocio
        }
    println(respuestaReduce);
    //return@reduce acumulado + (itemCarrito.cantidad * itemCarrito.precio)

}//termina la funcion Main

//void -> Unit
   fun imprimirNombre(nombre:String): Unit{
       println("Nombre: ${nombre}") //template Strings
   }

   fun calcularSueldo(
       sueldo: Double,  //Requerido
       tasa: Double=12.00,  // Opcional (defecto)
       bonoEspecial:Double?=null //Opcional (nullable)
   ):Double{
       //Int -> Int? (nullable)
       //String -> String? (nullable)
       //Date ->Date? (nullable)
      if(bonoEspecial==null){
          return  sueldo * (100/tasa)
      }else{
          return sueldo * (100/tasa) * bonoEspecial
      }
   }

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}




abstract class Numeros( // CONSTRUCTOr Primario
    //Caso 1) parametro normal
    // uno:Init, (parametro sin modificar acceso)

    //Caso 2) Parametro y propiedad (atributo)
    // private var uno: Int (Propiedad "instancia.uno")

    //Caso 3) Parametro y propiedad publica(atributo)
    //var uno:Int (propiedad "instancias.uno") (public)
    protected val numeroUno:Int,
    protected val numeroDos:Int,
){
    init { //bloque constructor primario
        println("Inicializando")
    }
}

class Suma( //constructor primario
   unoParametro: Int,
   dosParametro: Int,
): Numeros(
    unoParametro,
    dosParametro
  ){
    public val soyPublicoExplicito: String = "Explicito" //Publicos
    val soyPublicoImplicito: String = "Implicito" //publicos(propiedades, metodos)
    init{//bloque codigo Contructor primario
        this.numeroUno
        this.numeroDos
        numeroUno//this.opcional (propiedades, metodos)
        numeroDos//this.opcional (propiedades, metodos)
        this.soyPublicoExplicito
        soyPublicoImplicito //this opcional (propiedades, metodos)
    }

    constructor(//constructo secundario
        uno: Int?,
        dos: Int
    ):this(
        if(uno == null) 0 else uno,
        dos
    )

    constructor(//constructor tercero
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,

    )

    constructor(//constructor cuarto
        uno: Int?,
        dos: Int?
    ):this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,

        )

    //public fun suma():int{ (opcional "public")
    fun sumar():Int{
        val total = numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{//Comparte entre todas las instancias , similar al static
        //funciones y variables
        val pi=3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}



















