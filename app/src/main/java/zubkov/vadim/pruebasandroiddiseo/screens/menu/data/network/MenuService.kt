package zubkov.vadim.pruebasandroiddiseo.screens.menu.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.core.network.RetrofitHelper
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import javax.inject.Inject

class MenuService @Inject constructor(
    private val menuClient: MenuClient
){
    suspend fun getRoutes():List<MenuDTO>{
        return withContext(Dispatchers.IO){
            val response = menuClient.getRoutesList()
            response.body()?.data ?: emptyList()
        }
    }
}