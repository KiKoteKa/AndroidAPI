package zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.usecase

import zubkov.vadim.pruebasandroiddiseo.screens.menu.domin.entity.MenuModel
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.MenuRepository
import zubkov.vadim.pruebasandroiddiseo.screens.menu.data.dto.MenuDTO
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val repository : MenuRepository
) {
    suspend operator fun invoke() : List<MenuDTO>{
        return repository.getRouteRepository()
    }
}