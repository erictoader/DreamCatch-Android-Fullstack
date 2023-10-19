package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.runtime.Composable
import ps.erictoader.domain.feature.home.model.Dream

open class ChartDecorator(private val chart: ChartStrategy) : ChartStrategy {

    @Composable
    override fun Draw(dataset: List<Dream>) = chart.Draw(dataset = dataset)
}
