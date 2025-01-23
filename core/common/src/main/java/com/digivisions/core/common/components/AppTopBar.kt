package com.digivisions.core.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digivisions.core.common.R
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {

    CenterAlignedTopAppBar(
        title = { Image(painterResource(R.drawable.logo), contentDescription = null, modifier = Modifier.size(80.dp)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = getColor(AppColors.AppBarBackground)),
        actions = {
            IconButton(onClick = {

            }) {
                Icon(painter = painterResource(com.digivisions.core.common.R.drawable.ic_search), tint = getColor(AppColors.Tint), modifier = Modifier.size(32.dp), contentDescription = null)
            }
        }
    )
}


