package zubkov.vadim.pruebasandroiddiseo.screens.menu.data

import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.network.MenuService
import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val api : MenuService
) {
    suspend fun getRouteRepository():List<MenuDTO>{
        return api.getRoutes()
    }
}