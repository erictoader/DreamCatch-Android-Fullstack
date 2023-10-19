package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.axisTickComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.horizontalGradient
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import ps.erictoader.domain.feature.home.model.Dream

class DetailedChartStrategy : ChartStrategy {

    @Composable
    override fun Draw(dataset: List<Dream>) {
        val duration = dataset.map { it.sleepDuration }.toTypedArray()
        val energy = dataset.map { it.energyLevel }.toTypedArray()
        val stress = dataset.map { it.stress }.toTypedArray()
        val chartEntryModel =
            entryModelOf(
                entriesOf(*duration),
                entriesOf(*energy),
                entriesOf(*stress)
            )

        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(lineColor = colors.yellowTint),
                    lineSpec(lineColor = colors.cyanTint),
                    lineSpec(lineColor = colors.blushTint)
                )
            ),
            model = chartEntryModel,
            startAxis = startAxis(
                label = null,
                axis = axisLineComponent(
                    color = colors.text,
                    dynamicShader = verticalGradient(
                        arrayOf(colors.text.copy(.25f), colors.text.copy(.75f))
                    )
                ),
                guideline = null,
                tick = null
            ),
            bottomAxis = bottomAxis(
                label = axisLabelComponent(
                    color = colors.text.copy(.5f)
                ),
                axis = axisLineComponent(
                    color = colors.text,
                    dynamicShader = horizontalGradient(
                        arrayOf(colors.text.copy(.75f), colors.text.copy(.25f))
                    )
                ),
                guideline = axisGuidelineComponent(
                    color = colors.text,
                    dynamicShader = verticalGradient(
                        arrayOf(colors.text.copy(0f), colors.text.copy(.5f))
                    )
                ),
                tick = axisTickComponent(
                    color = colors.text.copy(.5f),
                    dynamicShader = null
                )
            ),
            legend = rememberLegend()
        )
    }

    @Composable
    private fun rememberLegend() = verticalLegend(
        items = listOf(
            verticalLegendItem(
                icon = shapeComponent(Shapes.pillShape, colors.yellowTint),
                label = textComponent(
                    color = colors.ghostTint,
                    textSize = 12.sp,
                ),
                labelText = "Sleep duration"
            ),
            verticalLegendItem(
                icon = shapeComponent(Shapes.pillShape, colors.cyanTint),
                label = textComponent(
                    color = colors.ghostTint,
                    textSize = 12.sp,
                ),
                labelText = "Energy level"
            ),
            verticalLegendItem(
                icon = shapeComponent(Shapes.pillShape, colors.blushTint),
                label = textComponent(
                    color = colors.ghostTint,
                    textSize = 12.sp,
                ),
                labelText = "Stress level"
            )
        ),
        iconSize = 10.dp,
        iconPadding = 6.dp,
        spacing = 0.dp,
        padding = dimensionsOf(
            start = 16.dp,
            top = 8.dp
        ),
    )
}
