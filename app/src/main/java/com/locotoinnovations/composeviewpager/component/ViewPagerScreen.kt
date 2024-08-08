package com.locotoinnovations.composeviewpager.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun HorizontalPagerScreen() {
    // create a listener pager state
    // create horizontal pager
    val scope = rememberCoroutineScope()
}

@Composable
fun VerticalPagerScreen() {

}

@PreviewLightDark
@Composable
fun HorizontalPagerScreenPreview() {
    HorizontalPagerScreen()
}

@PreviewLightDark
@Composable
fun VerticalPagerScreenPreview() {
    VerticalPagerScreen()
}