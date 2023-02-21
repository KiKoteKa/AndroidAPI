package zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.data.network.response.MapResponse
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO

interface MapClient {
    @POST("/publications")
    suspend fun doMaps(@Body map: MenuDTO): Response<MapResponse>
}