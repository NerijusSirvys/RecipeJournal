package com.nsdevelopment.mobile.components.slidingButton

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsdevelopment.mobile.ui.theme.ApplicationTheme
import com.nsdevelopment.mobile.ui.theme.Primary
import com.nsdevelopment.mobile.ui.theme.PrimaryText
import com.nsdevelopment.mobile.utilities.SystemPreviews

@Composable
fun SlidingButton(
    modifier: Modifier = Modifier,
    leftSide: String,
    rightSide: String,
    onClick: (SlidingButtonOption) -> Unit
) {
    var segmentWidth: Float by remember { mutableFloatStateOf(0f) }
    var selectedSide by remember { mutableStateOf(SlidingButtonOption.Left) }
    val density = LocalDensity.current

    val highlightOffset by animateDpAsState(
        targetValue = if (selectedSide == SlidingButtonOption.Left) 0.dp else segmentWidth.dp,
        animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing),
        label = "SelectedSegmentOffset"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color(0xFF333333), RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = modifier
                .offset(x = highlightOffset)
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .background(Color(0xFFFFC107), RoundedCornerShape(8.dp))
        )

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 5.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedSide = SlidingButtonOption.Left
                        onClick.invoke(SlidingButtonOption.Left)
                    }
            ) {
                Text(
                    text = leftSide,
                    color = if (selectedSide == SlidingButtonOption.Left) Primary else PrimaryText,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .onGloballyPositioned { coordinates ->
                        segmentWidth = coordinates.size.width / density.density
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedSide = SlidingButtonOption.Right
                        onClick.invoke(SlidingButtonOption.Right)
                    }
            ) {
                Text(
                    text = rightSide,
                    color = if (selectedSide == SlidingButtonOption.Right) Primary else PrimaryText,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@SystemPreviews
@Composable
private fun Preview() {
    ApplicationTheme() {
        Surface() {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                SlidingButton(
                    leftSide = "All",
                    rightSide = "Favourites",
                    onClick = {}
                )
            }
        }
    }
}