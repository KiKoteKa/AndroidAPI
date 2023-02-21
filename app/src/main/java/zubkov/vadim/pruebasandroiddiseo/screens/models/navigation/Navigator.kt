package zubkov.vadim.pruebasandroiddiseo.screens.models.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.ui.MapViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.menu.ui.Menu
import zubkov.vadim.pruebasandroiddiseo.screens.menu.ui.MenuViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.login.Login
import zubkov.vadim.pruebasandroiddiseo.screens.login.ui.UserViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.GoogleMapsView
import zubkov.vadim.pruebasandroiddiseo.screens.mapscreen.MapaGoogle
import zubkov.vadim.pruebasandroiddiseo.screens.register.RegisterScreen
import zubkov.vadim.pruebasandroiddiseo.screens.register.ui.viewmodel.RegisterViewModel
import zubkov.vadim.pruebasandroiddiseo.screens.register.ui.views.RegisterLastScreen
import zubkov.vadim.pruebasandroiddiseo.screens.register.ui.views.RegisterMiddleScreen
import zubkov.vadim.pruebasandroiddiseo.screens.splashscreen.SplashScreen
import zubkov.vadim.pruebasandroiddiseo.screens.users.PersonScreen
import zubkov.vadim.pruebasandroiddiseo.screens.users.ProfileDetail
import zubkov.vadim.pruebasandroiddiseo.screens.users.ui.PersonViewModel
import javax.inject.Singleton

@Composable
fun CustomNavigator(
    userViewModel: UserViewModel,
    mapViewModel : MapViewModel,
    registerViewModel : RegisterViewModel,
    menuViewModel: MenuViewModel,
    personViewModel : PersonViewModel
) {
    @Singleton
    val navigationController = rememberNavController()

    menuViewModel.devolverLista()
    NavHost(navController = navigationController, startDestination = Routes.SplashScreen.route) {
        composable(route = Routes.SplashScreen.route){
            SplashScreen(navigationController = navigationController)
        }
        composable(route = Routes.Login.route) {
            Login(navigationController = navigationController, userViewModel = userViewModel)
        }
        composable(route = Routes.Register.route) {
            RegisterScreen(navigationController = navigationController, registerViewModel = registerViewModel)
        }
        composable(route = Routes.RegisterMiddle.route) {
            RegisterMiddleScreen(navigationController = navigationController, registerViewModel = registerViewModel)
        }
        composable(route = Routes.RegisterLast.route) {
            RegisterLastScreen(navigationController = navigationController, registerViewModel = registerViewModel)
        }
        composable(route = Routes.Home.route) {
            personViewModel.returnPerson(userViewModel)
            Menu(navigationController = navigationController, menuViewModel = menuViewModel,mapViewModel = mapViewModel)
        }
        composable(route = Routes.GmapScreen.route) {
            MapaGoogle(navigationController = navigationController, mapViewModel = mapViewModel, userViewModel = userViewModel)
        }
        composable(route = Routes.GmapPost.route) {
            GoogleMapsView(navigationController = navigationController, mapViewModel = mapViewModel)
        }
        composable(route = Routes.Person.route) {
            PersonScreen(navigationController = navigationController,personViewModel,userViewModel)
        }
        composable(route = Routes.PersonDetail.route) {
            ProfileDetail(navigationController = navigationController,personViewModel,userViewModel)
        }
    }
}