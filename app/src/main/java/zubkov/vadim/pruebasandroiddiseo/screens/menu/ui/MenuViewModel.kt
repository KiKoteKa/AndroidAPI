package zubkov.vadim.pruebasandroiddiseo.screens.menu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.usecase.MenuUseCase
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {
    private var _RoutesList = MutableLiveData<List<MenuDTO>>()
    var routesList : LiveData<List<MenuDTO>> = _RoutesList

    fun changeList(){
        routesList = _RoutesList
    }

    fun devolverLista(){
        viewModelScope.launch {
            _RoutesList = MutableLiveData(menuUseCase())
            changeList()
        }
    }

    private var _actualRoute = MutableLiveData<MenuDTO>()
    var actualRoute : LiveData<MenuDTO> = _actualRoute

    fun updateActualRoute(route:MenuDTO){
        _actualRoute.value = route
    }

}