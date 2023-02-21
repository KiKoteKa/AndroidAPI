package zubkov.vadim.pruebasandroiddiseo.screens.login.domin.usecase

import zubkov.vadim.pruebasandroiddiseo.screens.login.data.UserRepository
import zubkov.vadim.pruebasandroiddiseo.screens.login.data.dto.UserDTO
import zubkov.vadim.pruebasandroiddiseo.screens.login.domin.entity.UserModel
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        user: UserModel
    ): Boolean {
        return repository.doLogin(
            user = UserDTO(
                email = user.email,
                password = user.password
            )
        )
    }
}