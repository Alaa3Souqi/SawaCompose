package com.aspire.sawacompose.screens.home.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aspire.sawacompose.R
import com.aspire.sawacompose.model.Category
import com.aspire.sawacompose.model.Place
import com.aspire.sawacompose.ui.theme.*
import com.aspire.sawacompose.unitls.Constraints
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeBottomSheet(
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetPeekHeight = 320.dp,
        sheetContent = {
            Column(Modifier.padding(top = 16.dp)) {
                Surface(
                    Modifier
                        .padding(bottom = 27.5.dp)
                        .width(75.dp)
                        .height(6.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Gray,
                    shape = CircleShape
                ) {}

                Card(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .height(62.dp), elevation = 3.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(4.dp),
                        value = "",
                        label = {
                            Text(
                                text = stringResource(R.string.where_to),
                                color = TextColorHint
                            )
                        },

                        onValueChange = {},
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = EditTextBackground,
                            unfocusedBorderColor = EditTextBackground,
                            focusedBorderColor = EditTextBackground,
                            textColor = MainTextColor,
                            cursorColor = MainTextColor,
                        ), trailingIcon = {
                            Image(
                                Icons.Default.Clear,
                                contentDescription = ""
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                CategoryList()
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.nearby_places),
                    Modifier.padding(start = 16.dp, bottom = 11.dp),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 0.dp
                    )
                ) {
                    items(Constraints.placeList) {
                        NearPlacesItem(it)
                    }
                }
            }
        },
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            MaterialTheme.colors.secondary
                        )
                    )
                )
                .fillMaxSize(),
        ) {

            Column(
                Modifier
                    .padding(bottom = 350.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.drawable.ic_home_button),
                    contentDescription = "",
                )

                Text(
                    stringResource(R.string.tap_to_check_in),
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 21.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
                    .align(Alignment.TopStart)
            ) {
                Image(
                    painterResource(R.drawable.ic_sawa_horizontal),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painterResource(R.drawable.ic_menu),
                    contentDescription = "",
                    Modifier.clickable {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun NearPlacesItem(place: Place) {
    val capacityStateColor =
        when (place.capacityState) {
            Constraints.MODERATE -> CapacityModerator
            Constraints.CLOSED -> CapacityClosed
            Constraints.LIGHT -> CapacityLight
            Constraints.CROWDED -> CapacityCrowded
            else -> CapacityLight
        }

    Card(
        Modifier
            .padding(bottom = 24.dp, top = 1.dp, start = 2.dp, end = 2.dp)
            .height(140.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(7.dp),
        elevation = 2.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 17.dp, end = 14.dp)
        ) {
            Row {
                Text(
                    text = place.name,
                    Modifier
                        .padding(top = 14.dp, end = 8.dp)
                        .weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(place.image),
                    contentDescription = "icon",
                    Modifier
                        .size(44.dp)
                        .padding(top = 10.dp)
                )
            }
            Text(
                text = place.distance,
                Modifier.padding(top = 9.dp),
                color = DistanceTextColor,
                fontSize = 12.sp
            )
            Text(
                text = place.opened,
                Modifier.padding(top = 9.dp),
                color = if (place.capacityState == Constraints.CLOSED)
                    CapacityCrowded
                else
                    MainTextColor,
                fontSize = 12.sp
            )
            Row(Modifier.padding(top = 9.dp)) {
                Text(
                    text = place.capacity,
                    fontSize = 12.sp
                )
                Text(
                    text = place.capacityState,
                    Modifier
                        .padding(start = 6.dp)
                        .background(shape = CircleShape, color = capacityStateColor)
                        .padding(horizontal = 10.dp),
                    color = Color.White,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun CategoryList() {
    LazyRow(contentPadding = PaddingValues(start = 16.dp, end = 8.dp)) {
        items(Constraints.categoryList) {
            CategoryItem(it)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Surface(
        Modifier
            .height(93.dp)
            .defaultMinSize(minWidth = 86.dp)
            .padding(end = 8.dp),
        color = colorResource(category.color),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painterResource(id = category.image), "", Modifier.size(25.dp))
            Text(
                stringResource(category.name),
                Modifier.padding(horizontal = 16.dp),
                fontSize = 14.sp,
                maxLines = 1,
            )
        }
    }
}