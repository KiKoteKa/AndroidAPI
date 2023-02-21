package zubkov.vadim.pruebasandroiddiseo.screens.users

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.screens.login.ui.UserViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.users.components.Header
import zubkov.vadim.pruebasandroiddiseo.screens.users.data.dto.PersonDTO
import zubkov.vadim.pruebasandroiddiseo.screens.users.ui.PersonViewModel
import java.text.SimpleDateFormat

@Composable
fun ProfileDetail(navigationController: NavHostController,personViewModel: PersonViewModel,userViewModel: UserViewModel){
    /*
    val usuario = StaticData().getUsuarios()[idUsuario]
    val usuarioPropio = globalViewModel.usuarioRegistrado?.value?.id == usuario.id
    */

    personViewModel.returnPerson(userViewModel)
    val user = personViewModel.person.value!!.first()

    Column(modifier = Modifier.fillMaxWidth().background(Color.LightGray)) {
        Header(
            name = user.nick,
            true,
            false,
            navigationController
        )
        PerfilUsuarioDetalleComp(user,true)
    }
}

@Composable
fun PerfilUsuarioDetalleComp(usuario: PersonDTO,editable:Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .border(border = BorderStroke(0.3.dp, Color.Black))
            .verticalScroll(rememberScrollState()))
    {
        Spacer(modifier = Modifier.height(25.dp))
        ImagenRedonda(R.drawable.fotoperfil)
        Texto(usuario.name, " Nombre")
        Texto(usuario.lastname, " Apellidos", Icons.Default.AccountBox)
        Texto(usuario.email, " Email", Icons.Default.Email)
        Texto(usuario.date, " F.Nacimiento", Icons.Default.DateRange)
        TextoDescripcion("No hay descripción disponible","Descripción")
        if (editable) {
            BotonEditarPerfil(text = "Editar Perfil ", icon = Icons.Default.Settings)
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun Texto(valor:String,texto:String,icono:ImageVector = Icons.Default.Person){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp, 2.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 3), horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = icono, contentDescription = texto, tint = MaterialTheme.colors.primaryVariant)
                Text(
                    text = texto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp*2/ 3), horizontalArrangement = Arrangement.End) {
                Text(
                    color = Color.White,
                    text = valor,
                    fontSize = 16.sp
                )
            }
        }
        Divider(color = Color.White, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp)
            .height(1.3.dp))
    }
}

@Composable
fun TextoDescripcion(valor:String,texto:String){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp, 2.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                Text(
                    text = texto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, 10.dp)
                .height(0.6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    color = Color.White,
                    text = valor,
                    fontSize = 16.sp
                )
            }
        }
        Divider(color = Color.White, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp)
            .height(1.3.dp))
    }
}

@Composable
fun BotonEditarPerfil(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null

) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {}
            .width(200.dp)
            .height(50.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
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
                tint = Color.Black,
                modifier = Modifier
                    .padding(0.dp)
                    .size(21.dp)
            )
        }
    }
}

@Composable
fun Separador(){
    Divider(color = MaterialTheme.colors.primary, modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 20.dp)
        .height(1.dp))
}


@Composable
fun ImagenRedonda(idFoto:Int) {
    Image(
        painter = painterResource(idFoto),
        contentDescription = "Imagen",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}
