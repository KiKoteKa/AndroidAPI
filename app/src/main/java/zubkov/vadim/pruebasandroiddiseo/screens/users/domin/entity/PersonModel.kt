package zubkov.vadim.pruebasandroiddiseo.screens.users.domin.entity

data class PersonModel(
    val name : String,
    val lastname : String,
    val email : String,
    val date : String,
    val nick : String,
    val following : List<String>,
    val photo : String
)