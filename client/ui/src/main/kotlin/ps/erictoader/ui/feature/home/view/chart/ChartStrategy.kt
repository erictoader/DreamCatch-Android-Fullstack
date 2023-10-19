package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.ui.feature.home.view.style.HomeScreenColors
import ps.erictoader.ui.feature.home.view.style.HomeScreenDimens

interface ChartStrategy {
    val dimens: HomeScreenDimens
        get() = HomeScreenDimens
    val colors: HomeScreenColors
        get() = HomeScreenColors

    @Composable
    fun Draw(dataset: List<Dream>)

    @Composable
    fun SimpleChart(
        chartEntryModel: ChartEntryModel,
        tint: Color
    ) {
        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = tint
                    )
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
            )
        )
    }
}
