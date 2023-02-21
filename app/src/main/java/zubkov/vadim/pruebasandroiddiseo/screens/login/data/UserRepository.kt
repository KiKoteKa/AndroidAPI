package zubkov.vadim.pruebasandroiddiseo.screens.login.data

import zubkov.vadim.pruebasandroiddiseo.screens.login.data.dto.UserDTO
import zubkov.vadim.pruebasandroiddiseo.screens.login.data.network.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api : UserService
){
    suspend fun doLogin(user : UserDTO) : Boolean {
        return api.doUser(user)
    }
}