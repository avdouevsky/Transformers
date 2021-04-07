package com.aequilibrium.transformers.model.transformers.remote

import com.google.gson.annotations.SerializedName

data class QueryTransformersResponse(
    @SerializedName("transformers")
    val transformers: List<TransformerDto>?
)