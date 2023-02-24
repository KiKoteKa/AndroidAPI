package zubkov.vadim.pruebasandroiddiseo.screens.users.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.screens.models.navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO


@ExperimentalFoundationApi
@Composable
fun Profile(navigationController: NavHostController, user:PersonDTO){

        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)) {
            Header(
                name = user.nick,
                false,
                false,
                navigationController
            )
            Spacer(modifier = Modifier.height(4.dp))
            ProfileSection(user)
            Spacer(modifier = Modifier.height(25.dp))
            ButtonSection(
                navigationController,
                modifier = Modifier.fillMaxWidth(),
                user,
                true
            )
            Spacer(modifier = Modifier.height(25.dp))

            PostTabView(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(0.dp, 0.dp, 0.dp, 2.dp),
                imageWithTexts = listOf(
                    ImageWithText(
                        image = painterResource(id = R.drawable.rutas),
                        text = "Rutas"
                    ),
                    ImageWithText(
                        image = painterResource(id = R.drawable.corazon),
                        text = "Likes"
                    ),
                    ImageWithText(
                        image = painterResource(id = R.drawable.seguidos),
                        text = "Seguidos"
                    ),
                    ImageWithText(
                        image = painterResource(id = R.drawable.seguidores),
                        text = "Seguidores"
                    ),
                )
            ) {
                selectedTabIndex = it
            }
            /*
            when (selectedTabIndex) {

                0 ->
                    MostrarRutas(
                    idRutas = usuario.rutas,
                    modifier = Modifier.fillMaxWidth(),
                    navigationController
                )
                1 -> MostrarRutas(
                    idRutas = usuario.rutasSeguidas,
                    modifier = Modifier.fillMaxWidth(),
                    navigationController
                )
                2 -> MostrarUsuarios(
                    usuario.seguidos,
                    modifier = Modifier.fillMaxWidth(),
                    navigationController
                )
                3 -> MostrarUsuarios(
                    usuario.seguidores,
                    modifier = Modifier.fillMaxWidth(),
                    navigationController
                )
            }

             */
        }
    }




@Composable
fun Header(
    name: String,
    mostrarAtras : Boolean,
    mostrarlogout : Boolean,
    navigationController: NavHostController,
    //globalViewModel: GlobalViewModel
) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            if(mostrarAtras) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            navigationController.navigateUp()
                        }
                )
            }

            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )

            if (mostrarlogout) {
                IconButton(enabled = true,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        /*globalViewModel.logout()
                              navigationController.navigate(Routes.Home.route)*/
                              },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ExitToApp,
                        contentDescription = "LogOut",
                        tint = Color.Black
                    )
                }
            }

    }
}

@Composable
fun ProfileSection(
    usuario: PersonDTO,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.fotoperfil),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f),usuario)
        }
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StatSection(modifier: Modifier = Modifier,usuario: PersonDTO) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(numberText = "0" , text = "Rutas")
        ProfileStat(numberText = "0"  , text = "Seguidores")
        ProfileStat(numberText = usuario.following.size.toString(), text = "Siguiendo")
    }
}

@Composable
fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun ButtonSection(
    navigationController: NavHostController,
    modifier: Modifier = Modifier,
    usuario:PersonDTO,
    perfilPropio : Boolean
) {
    val minWidth = 175.dp
    val height = 35.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        BotonDetalleUser(
            icon = Icons.Outlined.Person,
            text = "Datos  ",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height),
            navigationController = navigationController,
        )
        if (!perfilPropio) {
            BotonSeguirUser(
                modifier = Modifier
                    .defaultMinSize(minWidth = minWidth)
                    .height(height)
            )
        }
    }
}


@Composable
fun BotonDetalleUser(
modifier: Modifier = Modifier,
text: String? = null,
icon: ImageVector? = null,
navigationController: NavHostController
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                navigationController.navigate(Routes.PersonDetail.route)
            }
            .background(MaterialTheme.colors.background, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(6.dp)
    ) {
        if(text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        if(icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun BotonSeguirUser(
    modifier: Modifier = Modifier,
    //globalViewModel: GlobalViewModel,
    //idUser:Int
) {

    //val usuarioSiguiendo = globalViewModel.usuarioRegistrado.value!!.seguidos
    var siguiendo by remember {mutableStateOf(true)}

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                siguiendo = !siguiendo
            }
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(6.dp)
    ) {
        Text(
            text = if (siguiendo) "Dejar de seguir  " else "Seguir  ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        Icon(
            imageVector = if (siguiendo) Icons.Filled.Close else Icons.Filled.Check ,
            contentDescription = null,
            tint = if (siguiendo) Color.Red else Color.Green
        )
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier
    ) {
        imageWithTexts.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    tint = if(selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MostrarRutas(
    idRutas: List<Int>,
    modifier: Modifier = Modifier,
    navigationController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
            .fillMaxSize()
    ) {
        items(idRutas.size) {
            //CardRuta(StaticData().getRuta(it),navigationController)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MostrarUsuarios(
    idUsers: List<Int>,
    modifier: Modifier = Modifier,
    navigationController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier
            .scale(1.01f)
            .fillMaxHeight()
    ) {
        items(idUsers.size) {
            //CardUsuario(StaticData().getUsuario(it),navigationController)
        }
    }
}

data class ImageWithText(
    val image: Painter,
    val text: String
)