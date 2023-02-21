package zubkov.vadim.pruebasandroiddiseo.screens.login.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)
