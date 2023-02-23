package zubkov.vadim.pruebasandroiddiseo.screens.menu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.ui.MapViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import zubkov.vadim.pruebasandroiddiseo.screens.menu.ui.Components.*
import zubkov.vadim.pruebasandroiddiseo.screens.models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.screens.models.TopBarContent
import zubkov.vadim.pruebasandroiddiseo.screens.models.navigation.Routes

@Composable
fun Menu(menuViewModel: MenuViewModel, navigationController: NavHostController, mapViewModel: MapViewModel) {
    menuViewModel.devolverLista()
    var filter = FilterViewModel()
    filter.updateListadoBase(menuViewModel.routesList.value!!)
    filter.updateFiltroActividades(filter.categories.toMutableList())
    val listaRutas: List<MenuDTO> by filter.listado.observeAsState(initial = filter.listado.value!!)
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = {BottomBarContent(navigationController)},
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navigationController.navigate(Routes.GmapScreen.route)
                },
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ruta")
            }
        }
    ) {
        Column(){
            Buscador(filter)
            Filtros(filter)
            Separador()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE1E6E1))
                    .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
            ) {
                items(listaRutas) { ruta ->
                    MainCard(ruta,mapViewModel,navigationController,
                        onItemClicked = { card ->
                            menuViewModel.updateActualRoute(card)
                            navigationController.navigate(Routes.RouteDetail.route)
                        })
                }
            }
        }
    }
}


/*
@Composable
fun Menu(menuViewModel: MenuViewModel, navigationController: NavHostController, mapViewModel: MapViewModel) {
    menuViewModel.devolverLista()
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController) },
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE1E6E1))
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            items(menuViewModel.routesList.value!!.size) { ruta ->
                val route = menuViewModel.routesList.value!![ruta]
               MainCard(route,mapViewModel,navigationController)
            }
        }
    }
}

 */