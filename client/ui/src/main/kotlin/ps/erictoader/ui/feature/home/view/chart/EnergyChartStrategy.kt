package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.runtime.Composable
import com.patrykandpatrick.vico.core.entry.entryModelOf
import ps.erictoader.domain.feature.home.model.Dream

class EnergyChartStrategy : ChartStrategy {

    @Composable
    override fun Draw(dataset: List<Dream>) {
        val data = dataset.map { it.energyLevel }.toTypedArray()
        val chartEntryModel = entryModelOf(*data)

        SimpleChart(
            chartEntryModel = chartEntryModel,
            tint = colors.cyanTint
        )
    }
}
