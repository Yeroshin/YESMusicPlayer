package com.yes.trackdiialogfeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yes.trackdiialogfeature.domain.MediaQuery
import com.yes.trackdiialogfeature.domain.common.BaseResult
import com.yes.trackdiialogfeature.domain.entity.Menu
import com.yes.trackdiialogfeature.domain.usecase.GetRootMenu
import com.yes.trackdiialogfeature.domain.usecase.ShowChildMenu
import com.yes.trackdiialogfeature.presentation.entity.MenuUi
import com.yes.trackdiialogfeature.presentation.mapper.Mapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TrackDialogViewModel(
    private val showChildMenu: ShowChildMenu,
    private val getRootMenu: GetRootMenu,
    private val mapper: Mapper
) : ViewModel() {

    private val _stateItemsMedia =
        MutableStateFlow<TrackDialogViewModelState>(TrackDialogViewModelState.Init)
    val uiState: StateFlow<TrackDialogViewModelState> = _stateItemsMedia


    fun getMenu() {
        getRootMenu(viewModelScope) { result ->

            when(result){
                is BaseResult.Success->{
                    val item=mapper.map(result.data as Menu)
                    for (item in item.items){
                        item.onClick={ getMenuItemContent(item.title,item.name)}
                    }
                    successLoading(item)
                }
            }
        }
    }

    private fun successLoading(menu: MenuUi) {
        _stateItemsMedia.value = TrackDialogViewModelState.Success(menu)
    }

    fun getMenuItemContent(title: String,name:String) {

        showChildMenu(
            MediaQuery(
                title,
                name
            ),
            viewModelScope
        ) { result ->
            //_stateItemsMedia.value = it

            when (result) {
                is BaseResult.Success->{
                    val item=mapper.map(result.data as Menu)
                    for (item in item.items){
                        item.onClick={ getMenuItemContent(item.title,item.name)}
                    }
                    successLoading(item)
                }
            }
        }

    }
}

sealed class TrackDialogViewModelState {
    object Init : TrackDialogViewModelState()
    data class IsLoading(val isLoading: Boolean) : TrackDialogViewModelState()
    data class Success(val menu: MenuUi) : TrackDialogViewModelState() {
        val title: String = ""

    }
}