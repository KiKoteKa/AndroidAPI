package zubkov.vadim.pruebasandroiddiseo.screens.users.domin.usecase

import zubkov.vadim.pruebasandroiddiseo.screens.users.data.PersonRepository
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO
import zubkov.vadim.pruebasandroiddiseo.screens.users.domin.entity.PersonModel
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repository : PersonRepository
) {
    suspend operator fun invoke(email : String) : List<PersonDTO>{
        return repository.getPersonRepository(
            email = email
        )
    }

    suspend fun getFollowers(email:String):List<PersonDTO>{
        return repository.getFollowers(email)
    }

    suspend fun followUser(email:String, emailSeguir : String){
        repository.followUser(email,emailSeguir)
    }


    suspend fun unfollowUser(email:String, emailSeguir : String){
        repository.unfollowUser(email,emailSeguir)
    }
}