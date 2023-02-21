package zubkov.vadim.pruebasandroiddiseo.screens.register.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import zubkov.vadim.pruebasandroiddiseo.screens.register.data.dto.RegisterDTO
import zubkov.vadim.pruebasandroiddiseo.screens.register.data.network.response.RegisterResponse
import zubkov.vadim.pruebasandroiddiseo.screens.register.domin.entity.RegisterModel

interface RegisterClient {
    @POST("auth/signup")
    suspend fun doRegisterJSON(@Body registerUser: RegisterDTO): Response<RegisterResponse>
}