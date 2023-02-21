package zubkov.vadim.pruebasandroiddiseo.screens.login.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import zubkov.vadim.pruebasandroiddiseo.screens.login.data.dto.UserDTO
import zubkov.vadim.pruebasandroiddiseo.screens.login.data.network.response.UserResponse

interface UserClient {
    @POST("auth/signin")
    suspend fun doUser(
        @Body user: UserDTO
    ): Response<UserResponse>
}