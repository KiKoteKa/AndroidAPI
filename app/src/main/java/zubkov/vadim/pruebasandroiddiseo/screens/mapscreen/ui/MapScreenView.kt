package zubkov.vadim.pruebasandroiddiseo.screens.mapscreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.GoogleMap
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.ui.MapViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.screens.models.TopBarContent

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GoogleMapsView(navigationController: NavHostController, mapViewModel: MapViewModel) {
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val context : Context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController = navigationController) }
    ) {
        if (locationPermissionState.status.isGranted){
            Box(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
                    .background(Color.LightGray)
            ){
                Box(
                    Modifier
                        .padding(top = it.calculateTopPadding(), bottom = 60.dp)
                ){
                    GoogleMap(
                        properties = mapViewModel.properties,
                        uiSettings = mapViewModel.uiSettings
                    )
                }
                Button(
                    onClick = {
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener { location: Location? ->
                                location?.let { location ->
                                    Log.d("Lat B","${location.latitude}")
                                    Log.d("Long B","${location.longitude}")
                                }
                            }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(250.dp)
                        .height(60.dp)
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                    Text("Iniciar la Grabacion")
                }
            }
        } else {
            LaunchedEffect(locationPermissionState.status.isGranted){
                locationPermissionState.launchPermissionRequest()
            }
        }
    }
}