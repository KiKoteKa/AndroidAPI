package zubkov.vadim.pruebasandroiddiseo.screens.menu.ui.Components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.ui.MapViewModel
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import zubkov.vadim.pruebasandroiddiseo.screens.menu.ui.MenuViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.models.navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO

@Composable
fun MainCard(ruta: MenuDTO, mapViewModel: MapViewModel,navigationController : NavHostController,
             user:PersonDTO,menuViewModel: MenuViewModel,ocultarLike:Boolean=false,onItemClicked: (ruta: MenuDTO) -> Unit){
    val tarjetaDeUsuario = user.email == ruta.email
    Log.d("Rutas", user.fav_routes.toString())
    var likeAlready by remember {mutableStateOf(ruta._id in user.fav_routes)}
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(ruta) }),
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
                onClick = {
                    if (likeAlready){
                              menuViewModel.unlikeRuta(user.email,ruta._id)
                              likeAlready = false
                          }else{
                              menuViewModel.likeRuta(user.email,ruta._id)
                              likeAlready = true
                          }
                          },
                modifier = Modifier.size(48.dp)
            ) {
                if(!ocultarLike) {
                    if (!tarjetaDeUsuario) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "favorito",
                            tint = if (likeAlready) Color.Red else Color.LightGray
                        )
                    }
                }
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
                            .clickable { navigationController.navigate("extUsers/${ruta.email}")}
                    )
                    Text(
                        text = ruta.email,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(5.dp,0.dp,0.dp,0.dp)
                    )
                }
            }
        }
    }
}
