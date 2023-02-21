package zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.data

import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.data.network.MapService
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val api : MapService
){
    suspend fun doMaps(map : MenuDTO) : Boolean {
        return api.doMaps(map)
    }
}