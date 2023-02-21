package zubkov.vadim.pruebasandroiddiseo.screens.login.data.dto

import javax.inject.Inject

class UserDTOFactory @Inject constructor(){
    operator fun invoke(
        email: String,
        password: String
    ) : UserDTO {
        return UserDTO(
            email = email,
            password = password
        )
    }
}