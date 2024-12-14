package com.inforcap.exampleapiresttmdb.network.response

import com.google.gson.annotations.SerializedName

data class FigureResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("logo") val logo: String

)
