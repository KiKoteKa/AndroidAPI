package zubkov.vadim.pruebasandroiddiseo.screens.menu.ui.Models

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.ui.MapViewModel
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.screens.models.navigation.Routes
/*
@Composable
fun MainCard(contentRoutes: MenuDTO, mapViewModel: MapViewModel,navigationController : NavHostController){


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                navigationController.navigate(route = Routes.Person.route)
            },
        backgroundColor = Color(0xFF609D3E)
    ){
        Column() {
            Row(
                Modifier
                    .paint(
                        painterResource(id = R.drawable.foto),
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.65f,
                        colorFilter = ColorFilter.tint(Color(0xFF000000), BlendMode.Lighten)
                    )
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    RouteName(routeName = contentRoutes)
                    RouteType(routeType = contentRoutes)
                    Distance(distance = contentRoutes)
                    Image(punto = contentRoutes, mapViewModel = mapViewModel)
                }
            }
            DividerPers(
                modifier= Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.padding(5.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Date(date = contentRoutes)
            }
        }
    }
}
@Composable
fun DividerPers(modifier: Modifier){
    Divider(
        modifier = modifier
    )
}

@Composable
fun RouteType(routeType: MenuDTO){
    Text(
        text = routeType.category,
        fontSize = 15.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
fun RouteName(routeName: MenuDTO){
    Text(
        text = routeName.name,
        fontSize = 23.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
fun Distance(distance: MenuDTO){
    Text(
        text = "${distance.distance} Km",
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium ,
    )
}

@Composable
fun Duration(duration: MenuDTO){
    Box(){
        Column() {
            Text(
                text = "Desnivel",
                fontSize = 17.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${500} m",
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Image(punto: MenuDTO, mapViewModel: MapViewModel){
    Box(
        modifier = Modifier
            .size(150.dp)
    ){
        val locationPermissionState = rememberPermissionState(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val middlePoint = LatLng((punto.lat_A + punto.lng_A) / 2, (punto.lat_B + punto.lng_B) / 2)
        val cameraPosition = rememberCameraPositionState{
            position = CameraPosition.fromLatLngZoom(middlePoint,5f)
        }

        if (locationPermissionState.status.isGranted){
            GoogleMap(
                properties = mapViewModel.properties,
                uiSettings = mapViewModel.uiSettings,
                cameraPositionState = cameraPosition
            ){
                //Position A
                Marker(
                    state = MarkerState(
                        position = LatLng(punto.lat_A,punto.lng_A)
                    )
                )

                //Position B
                Marker(
                    state = MarkerState(
                        position = LatLng(punto.lat_B,punto.lng_B)
                    )
                )

                Polyline(
                    points = punto.rec_movement
                )
            }
        } else {
            LaunchedEffect(locationPermissionState.status.isGranted){
                locationPermissionState.launchPermissionRequest()
            }
        }
    }
}

@Composable
fun User(user: MenuDTO, icon: MenuModel){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 20.dp)
    ){
        /*var imagenes = when (icon.foto){
            "" -> R.drawable.person
            else -> R.drawable.person
        }*/
        Box(
            modifier = Modifier
                .size(30.dp)
                .border(BorderStroke(1.dp, Color.Black))
        ){
            /*Image(
                painterResource(id = imagenes),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )*/
        }
        Spacer(Modifier.padding(5.dp))
        /*Text(
            text = "${usuario.id_user}"
        )*/
    }

}

@Composable
fun Date(date: MenuDTO){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
        )
        Spacer(Modifier.padding(5.dp))
        Text(
            text = "${date.date}"
        )
    }
}

*/

@Composable
//fun Tarjeta(navigationController: NavHostController,globalViewModel: GlobalViewModel, ruta: Ruta, onItemClicked: (ruta: Ruta) -> Unit) {
fun MainCard(ruta: MenuDTO, mapViewModel: MapViewModel,navigationController : NavHostController){
    //val tarjetaDeUsuario = globalViewModel.usuarioRegistrado.value!!.id == ruta.usuarioPublicado.id
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable(onClick = { /*onItemClicked(ruta)*/ }),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = ruta.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
                )
            }
            IconButton(
                onClick = { /* Acción al hacer clic en el botón */ },
                modifier = Modifier.size(48.dp)
            ) {
                //if(!tarjetaDeUsuario) {
                    CorazonFavorito()
                //}
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp, 40.dp,16.dp,16.dp).fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(0.dp, 30.dp, 0.dp, 0.dp)
                    .weight(1f)
                    .width(10.dp)
            ) {
                Text(
                    text = "Distancia",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "${ruta.distance} km",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "Categoría",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = ruta.category,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "Dificultad",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = ruta.difficulty,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 10.dp)
                    .weight(2.25f)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp,30.dp,0.dp,0.dp)) {
                    Image(
                        painter = painterResource(R.drawable.background),
                        contentDescription = "Primera Imagen de la Ruta",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(400.dp, 120.dp)
                            .padding(top = 0.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
                    Image(
                        painter = painterResource(R.drawable.fotoperfil),
                        contentDescription = "Icono del Perfil del Usuario",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { /*navigationController.navigate("user/${ruta.usuarioPublicado.id}/true")*/ }
                    )
                    Text(
                        text = "nick", //" ${ruta.usuarioPublicado.nick}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(5.dp,0.dp,0.dp,0.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CorazonFavorito() {

    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    var enabled by remember { mutableStateOf(false) }

    val scale = remember { Animatable(1f) }

    Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "favorito",
        tint = if (enabled) Color.Red else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = 24.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                enabled = !enabled
                coroutineScope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100),
                    )
                }
            }
    )
}
