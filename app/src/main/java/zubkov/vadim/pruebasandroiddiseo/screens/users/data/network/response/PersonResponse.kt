package zubkov.vadim.pruebasandroiddiseo.screens.users.data.network.response

import com.google.gson.annotations.SerializedName
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO

data class PersonResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<PersonDTO>
)