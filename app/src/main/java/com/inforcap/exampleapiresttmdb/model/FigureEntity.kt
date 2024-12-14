package com.inforcap.exampleapiresttmdb.model

import com.google.gson.annotations.SerializedName

data class FigureEntity(

    @SerializedName("id")
    var id: Int,
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("logo")
    var logo: String

)
