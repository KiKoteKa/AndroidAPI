package zubkov.vadim.pruebasandroiddiseo.screens.menu.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    suspend fun likeRoute(email:String,idRuta:String){
        menuClient.likeRoute(LikeBody(email,idRuta))
    }
    suspend fun unlikeRoute(email:String, idRuta:String){
        menuClient.unlikeRoute(LikeBody(email,idRuta))
    }


}