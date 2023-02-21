package zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.data.network.response

import com.google.gson.annotations.SerializedName

data class MapResponse(
    @SerializedName("status") val status: String
)
