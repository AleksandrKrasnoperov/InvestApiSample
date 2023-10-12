package com.example.apisampleapp.data.model

import com.google.gson.annotations.SerializedName

data class ShareRequest(
    @SerializedName("id_type")
    val idType: InstrumentIdType,
    @SerializedName("class_code")
    val classCode: String?,
    val id: String
)
