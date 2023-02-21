package zubkov.vadim.pruebasandroiddiseo.screens.users.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.network.response.PersonResponse
import zubkov.vadim.pruebasandroiddiseo.screens.users.domin.entity.PersonModel

interface PersonClient {
    @GET("/users/{email}")
    suspend fun getPersonByEmail(
        @Path("email") email: String,
    ): Response<PersonResponse>
}