package zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto

import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO

data class PersonDTO(
    val name : String,
    val lastname : String,
    val email : String,
    val date : String,
    val nick : String,
    val following : List<String>,
    val photo : String
)