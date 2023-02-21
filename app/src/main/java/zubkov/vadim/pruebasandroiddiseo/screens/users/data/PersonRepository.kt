package zubkov.vadim.pruebasandroiddiseo.screens.users.data

import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.network.PersonService
import zubkov.vadim.pruebasandroiddiseo.screens.users.domin.entity.PersonModel
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val api : PersonService
) {
    suspend fun getPersonRepository(email: String):List<PersonDTO>{
        return api.getPerson(email)
    }
}