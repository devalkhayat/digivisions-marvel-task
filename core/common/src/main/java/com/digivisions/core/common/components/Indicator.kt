package com.digivisions.core.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.digivisions.core.common.theme.AppColors
import com.digivisions.core.common.theme.getColor
import kotlin.math.abs

private const val MULTIPLIER_SELECTED_PAGE = 4
private val baseWidth = 4.dp
private val spacing = 10.dp
private val height = 8.dp

//https://www.inovex.de/de/blog/author/jschamburger/
@Composable
fun CustomPagerIndicatorFirstTry(pagerState: PagerState, indicatorColor: AppColors) {
    Row {

        val offsetIntPart = pagerState.currentPageOffsetFraction.toInt()
        val offsetFractionalPart = pagerState.currentPageOffsetFraction - offsetIntPart
        val currentPage = pagerState.currentPage + offsetIntPart
        val targetPage = if (pagerState.currentPageOffsetFraction < 0) currentPage - 1 else currentPage + 1
        val currentPageWidth = baseWidth * (1 + (1 - abs(offsetFractionalPart)) * MULTIPLIER_SELECTED_PAGE)
        val targetPageWidth = baseWidth * (1 + abs(offsetFractionalPart) * MULTIPLIER_SELECTED_PAGE)


        repeat(pagerState.pageCount) { index ->
            val width = when (index) {
                currentPage -> currentPageWidth
                targetPage -> targetPageWidth
                else -> baseWidth
            }
            DrawCircle(background = indicatorColor, modifier = Modifier.size(width, height))


            if (index != pagerState.pageCount - 1) {
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }
}


@Composable
fun DrawCircle(background: AppColors, modifier: Modifier=Modifier, content: @Composable() (() -> Unit?)? =null){
    Box(modifier = modifier
        .fillMaxWidth()
        .clip(CircleShape)
        .background(getColor(background)
        ), contentAlignment = Alignment.Center
    ){
        if (content != null) {
            content()
        }
    }
}