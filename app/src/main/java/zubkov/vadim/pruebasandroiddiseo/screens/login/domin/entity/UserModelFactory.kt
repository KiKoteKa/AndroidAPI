package zubkov.vadim.pruebasandroiddiseo.screens.login.domin.entity

import javax.inject.Inject

class UserModelFactory @Inject constructor(){
    operator fun invoke(
        email: String,
        password: String
    ) : UserModel {
        return UserModel(
            email = email,
            password = password
        )
    }
}