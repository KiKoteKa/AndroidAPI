package zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.domin.usecase

import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.data.MapRepository
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import javax.inject.Inject

class MapUseCase @Inject constructor(
    private val repository: MapRepository
){
    suspend operator fun invoke(
        map : MenuModel
    ) : Boolean {
        return repository.doMaps(
            map = MenuDTO(
                email = map.email,
                date = map.date,
                name = map.name,
                category = map.category,
                distance = map.distance,
                difficulty = map.difficulty,
                duration = map.duration,
                description = map.description,
                privacy = map.privacy,
                photo = map.photo,
                lat_A = map.lat_A,
                lng_A = map.lng_A,
                lat_B = map.lat_B,
                lng_B = map.lng_B,
                rec_movement = map.rec_movement
            )
        )
    }
}