package zubkov.vadim.pruebasandroiddiseo.screens.menu.data.network

import retrofit2.Response
import retrofit2.http.GET
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.network.response.MenuResponse

interface MenuClient {
    @GET("/publications")
    suspend fun getRoutesList(): Response<MenuResponse>
}