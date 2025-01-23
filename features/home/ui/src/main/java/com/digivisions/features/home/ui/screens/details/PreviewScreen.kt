package com.digivisions.features.home.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.digivisions.core.common.components.AppLabel
import com.digivisions.core.common.components.LoadNetworkImage
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor

@Composable
fun PreviewScreen(navHostController: NavHostController, url:String, name:String){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp).background(color= getColor(AppColors.PopupBackground))) {

        IconButton(onClick = {navHostController.popBackStack()},modifier = Modifier.align(alignment = Alignment.End)) {
            Icon(painterResource(com.digivisions.core.common.R.drawable.ic_close), tint = getColor(AppColors.Tint2), contentDescription = null, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))

        LoadNetworkImage(url, modifier = Modifier.fillMaxWidth().weight(1f))

        Spacer(modifier = Modifier.height(50.dp))

        AppLabel(caption = name, style = MaterialTheme.typography.labelSmall, color= AppColors.Text3,modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(50.dp))
    }


}