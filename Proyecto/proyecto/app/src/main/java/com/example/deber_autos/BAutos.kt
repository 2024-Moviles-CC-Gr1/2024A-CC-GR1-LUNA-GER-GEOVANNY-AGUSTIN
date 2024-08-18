package com.example.deber_autos

import android.os.Parcel
import android.os.Parcelable

class BAutos (
    var id:Int,
    var nombreModelo:String,
    var precioModelo:Int,
    var tipomodelo:String,
    var carro: String
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ){

    }
    override fun toString(): String{
        return "$nombreModelo ${precioModelo}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombreModelo)
        parcel.writeInt(precioModelo)
        parcel.writeString(tipomodelo)
        parcel.writeString(carro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BAutos> {
        override fun createFromParcel(parcel: Parcel): BAutos {
            return BAutos(parcel)
        }

        override fun newArray(size: Int): Array<BAutos?> {
            return arrayOfNulls(size)
        }
    }
}