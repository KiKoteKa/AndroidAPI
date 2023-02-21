package zubkov.vadim.pruebasandroiddiseo.screens.register.data

import zubkov.vadim.pruebasandroiddiseo.screens.register.data.dto.RegisterDTO
import zubkov.vadim.pruebasandroiddiseo.screens.register.data.network.RegisterService
import zubkov.vadim.pruebasandroiddiseo.screens.register.domin.entity.RegisterModel
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val api : RegisterService
){
    suspend fun doRegister(registerUser : RegisterDTO) : Boolean {
        return api.doUser(registerUser)
    }
}