package zubkov.vadim.pruebasandroiddiseo.screens.login.data.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("status") val status: String,
    @SerializedName("accessToken") var accessToken: String
)
