package com.aspire.sawacompose.screens.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import com.aspire.sawacompose.screens.home.components.HomeBottomSheet
import com.aspire.sawacompose.screens.home.components.HomeNavigationDrawer
import com.aspire.sawacompose.viewmodel.SettingViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(viewModel: SettingViewModel) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeNavigationDrawer(
                backButton = {
                    scope.launch {
                        drawerState.close()
                    }
                }, viewModel = viewModel
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
        content = { HomeBottomSheet(scope, drawerState) }
    )
}

