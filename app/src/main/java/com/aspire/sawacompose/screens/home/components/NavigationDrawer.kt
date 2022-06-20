package com.aspire.sawacompose.screens.home.components

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.mvrx.compose.collectAsState
import com.aspire.sawacompose.screens.MainActivity
import com.aspire.sawacompose.R
import com.aspire.sawacompose.ui.theme.DividerColor
import com.aspire.sawacompose.ui.theme.MainTextColor
import com.aspire.sawacompose.ui.theme.UncheckedCheckBoxColor
import com.aspire.sawacompose.ui.theme.UncheckedTextColor
import com.aspire.sawacompose.unitls.Constraints.ARABIC_LABEL
import com.aspire.sawacompose.unitls.Constraints.ENGLISH
import com.aspire.sawacompose.unitls.Constraints.ENGLISH_LABEL
import com.aspire.sawacompose.unitls.Constraints.PINK
import com.aspire.sawacompose.viewmodel.SettingViewModel

@ExperimentalMaterialApi
@Composable
fun HomeNavigationDrawer(
    logging: Boolean = true,
    backButton: () -> Unit,
    viewModel: SettingViewModel
) {

    val mainActivity = (LocalContext.current as Activity) as MainActivity

    val state by viewModel.collectAsState()

    var isEnglish = false
    if (state.language == ENGLISH) isEnglish = true

    var isPink = false
    if (state.theme == PINK) isPink = true

    val languageOption1State = remember { mutableStateOf(isEnglish) }
    val languageOption2State = remember { mutableStateOf(!isEnglish) }

    val themeOption1State = remember { mutableStateOf(!isPink) }
    val themeOption2State = remember { mutableStateOf(isPink) }


    Column(Modifier.fillMaxSize()) {

        Icon(
            painterResource(R.drawable.ic_arrow_left),
            "",
            Modifier
                .padding(start = 28.dp, top = 42.dp)
                .clickable { backButton() },
            tint = MainTextColor
        )
        Spacer(Modifier.height(18.dp))
        Text(
            text = stringResource(R.string.menu),
            Modifier.padding(horizontal = 28.dp, vertical = 8.dp),
            fontSize = 40.sp
        )
        if (logging) {
            DrawerUserItem(
                title = stringResource(R.string.favorites),
                image = R.drawable.ic_heart
            )
            DrawerUserItem(
                title = stringResource(R.string.history),
                image = R.drawable.ic_history
            )
        }

        SettingNavigationItem(
            stringResource(R.string.language),
            R.drawable.ic_language,
            ENGLISH_LABEL,
            ARABIC_LABEL,
            option1State = languageOption1State,
            option2State = languageOption2State
        ) {
            mainActivity.viewModel.setLanguage(it)
            mainActivity.recreate()
        }

        SettingNavigationItem(
            stringResource(R.string.theme),
            R.drawable.ic_color_lens,
            stringResource(R.string.blue),
            stringResource(R.string.pink),
            option1State = themeOption1State,
            option2State = themeOption2State,
            showDriver = logging
        ) {
            mainActivity.viewModel.setTheme(it)
            mainActivity.recreate()
        }

        if (logging)
            DrawerUserItem(
                showDriver = false,
                title = stringResource(R.string.logout),
                image = R.drawable.ic_logout
            )
    }
}


@Composable
fun SettingNavigationItem(
    title: String,
    image: Int,
    option1: String,
    option2: String,
    option1State: MutableState<Boolean>,
    option2State: MutableState<Boolean>,
    showDriver: Boolean = true,
    onCheckedChange: (String) -> Unit
) {

    Column {
        DrawerSettingHeaderItem(false, title, image)
        Row(Modifier.padding(vertical = 12.dp)) {
            Spacer(modifier = Modifier.width(64.dp))
            LabelledCheckbox(
                checked = option1State.value,
                title = option1
            ) {
                option2State.value = !it
                option1State.value = it
                onCheckedChange(option1)
            }
            Spacer(modifier = Modifier.width(24.dp))
            LabelledCheckbox(checked = option2State.value, title = option2) {
                option2State.value = it
                option1State.value = !it
                onCheckedChange(option2)
            }
        }
        if (showDriver) Divider(Modifier.padding(horizontal = 16.dp), color = DividerColor)
    }
}

@Composable
fun LabelledCheckbox(
    checked: Boolean,
    title: String,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(
                onClick = {
                    if (!checked) {
                        onCheckedChange(!checked)
                    }
                }
            )
            .requiredHeight(ButtonDefaults.MinHeight)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                uncheckedColor = UncheckedCheckBoxColor,
                checkedColor = MaterialTheme.colors.primaryVariant
            )
        )
        Spacer(Modifier.size(6.dp))

        Text(
            text = title,
            color = if (checked) MaterialTheme.colors.primaryVariant else UncheckedTextColor,
            fontSize = 14.sp
        )
    }
}

@Composable
fun DrawerUserItem(
    showDriver: Boolean = true,
    title: String,
    image: Int,
    navigation: () -> Unit = {}
) {
    Column(
        Modifier
            .height(60.dp)
            .fillMaxWidth()
            .clickable { navigation() },
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(image),
                "",
                Modifier.padding(start = 32.dp, end = 17.dp),
                tint = MaterialTheme.colors.primaryVariant
            )
            Text(text = title, fontSize = 16.sp)
        }

        if (showDriver) Divider(Modifier.padding(horizontal = 16.dp), color = DividerColor)

    }
}

@Composable
fun DrawerSettingHeaderItem(
    showDriver: Boolean = true,
    title: String,
    image: Int
) {
    Column(Modifier.padding(top = 18.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(image),
                "",
                Modifier.padding(start = 32.dp, end = 17.dp),
                tint = MaterialTheme.colors.primaryVariant
            )
            Text(text = title, fontSize = 16.sp)
        }

        if (showDriver) Divider(Modifier.padding(horizontal = 16.dp), color = DividerColor)

    }
}