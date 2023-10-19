package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.runtime.Composable
import ps.erictoader.domain.feature.home.model.Dream

object ChartFactory {

    @Composable
    fun Draw(strategy: ChartStrategy, dataset: List<Dream>) = strategy.Draw(dataset)
}
