package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ps.erictoader.domain.feature.home.model.Dream

class ChartPositiveTrendDecorator(chart: ChartStrategy) : ChartDecorator(chart) {

    @Composable
    override fun Draw(dataset: List<Dream>) {
        var topPaddingTarget by remember { mutableStateOf(100) }
        var arrowAlphaTarget by remember { mutableStateOf(0f) }
        val topPaddingValue by animateIntAsState(
            targetValue = topPaddingTarget,
            animationSpec = tween(500)
        )
        val arrowAlphaValue by animateFloatAsState(
            targetValue = arrowAlphaTarget,
            animationSpec = tween(1000)
        )

        Surface(color = Color.Transparent) {
            super.Draw(dataset)
            Icon(
                modifier = Modifier
                    .padding(top = topPaddingValue.dp)
                    .alpha(arrowAlphaValue),
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = null,
                tint = Color.Green
            )
        }

        LaunchedEffect(key1 = Unit) {
            topPaddingTarget = 0
            arrowAlphaTarget = 1f
        }
    }
}
