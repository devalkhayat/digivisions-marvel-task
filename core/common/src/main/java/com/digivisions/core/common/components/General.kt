package com.digivisions.core.common.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.digivisions.core.common.R
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ScreenPlaceHolder(modifier: Modifier=Modifier,content: @Composable ()->Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        content()
    }
}


@Composable
fun LoadNetworkImage(url:String,modifier: Modifier=Modifier,scale:ContentScale=ContentScale.Crop){
    
    AsyncImage(
        model = url,
        contentDescription = null,
        //error = painterResource(com.digivisions.core.common.R.drawable.ic_humidity),
        onError = {
            Log.d("neo7070", "Display: ${it.result.throwable.message}")
        },
        onLoading = {

        },
        contentScale = scale,
        modifier = modifier
            .fillMaxSize()
    )
}

@Composable
fun CircleLoading(){
    CircularProgressIndicator(color = getColor(AppColors.CircleProgress),strokeWidth = 5.dp)
}