package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.runtime.Composable
import com.patrykandpatrick.vico.core.entry.entryModelOf
import ps.erictoader.domain.feature.home.model.Dream

class StressChartStrategy : ChartStrategy {

    @Composable
    override fun Draw(dataset: List<Dream>) {
        val data = dataset.map { it.stress }.toTypedArray()
        val chartEntryModel = entryModelOf(*data)

        SimpleChart(
            chartEntryModel = chartEntryModel,
            tint = colors.blushTint
        )
    }
}
