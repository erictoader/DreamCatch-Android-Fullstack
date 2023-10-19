package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import ps.erictoader.domain.feature.home.model.Dream

class ChartNegativeTrendDecorator(chart: ChartStrategy) : ChartDecorator(chart) {

    @Composable
    override fun Draw(dataset: List<Dream>) {
        var arrowAlphaTarget by remember { mutableStateOf(0f) }
        val arrowAlphaValue by animateFloatAsState(
            targetValue = arrowAlphaTarget,
            animationSpec = tween(1000)
        )

        Surface(color = Color.Transparent) {
            super.Draw(dataset)
            Icon(
                modifier = Modifier.alpha(arrowAlphaValue),
                imageVector = Icons.Default.ArrowDownward,
                contentDescription = null,
                tint = Color.Red
            )

        }

        LaunchedEffect(key1 = Unit) {
            arrowAlphaTarget = 1f
        }
    }
}
