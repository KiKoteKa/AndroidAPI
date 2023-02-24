package zubkov.vadim.pruebasandroiddiseo.screens.menu.data.network

import com.google.gson.annotations.SerializedName

class LikeBody (
    @SerializedName("email") var email: String,
    @SerializedName("route") var route: String,
)