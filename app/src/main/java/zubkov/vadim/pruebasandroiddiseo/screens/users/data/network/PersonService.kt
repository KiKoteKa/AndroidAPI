package zubkov.vadim.pruebasandroiddiseo.screens.users.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO
import zubkov.vadim.pruebasandroiddiseo.screens.users.domin.entity.PersonModel
import javax.inject.Inject

class PersonService @Inject constructor(
    private val personClient: PersonClient
){
    suspend fun getPerson(
        email: String
    ):List<PersonDTO>{
        return withContext(Dispatchers.IO){
            Log.d("Email Service","$email")
            val response = personClient.getPersonByEmail(email)
            response.body()?.data ?: emptyList()
        }
    }
}