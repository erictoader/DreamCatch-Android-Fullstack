package ps.erictoader.ui.feature.home.view.chart

import androidx.compose.ui.graphics.Color

enum class ChartType {
    SLEEP_SCORE_CHART,
    DURATION_CHART,
    ENERGY_CHART,
    STRESS_CHART,
    DETAILED_CHART;

    private val yellowTint = Color(0xFFF8EE68)
    private val cyanTint = Color(0xFF25A18E)
    private val blushTint = Color(0xFFD55672)
    private val ghostTint = Color(0xFFD1CCDC)

    fun getStrategy(): ChartStrategy =
        when (this) {
            SLEEP_SCORE_CHART -> SleepScoreChartStrategy()
            DURATION_CHART -> DurationChartStrategy()
            ENERGY_CHART -> EnergyChartStrategy()
            STRESS_CHART -> StressChartStrategy()
            DETAILED_CHART -> DetailedChartStrategy()
        }

    fun getChartName(): String =
        when (this) {
            SLEEP_SCORE_CHART -> "Sleep Score"
            DURATION_CHART -> "Sleep Duration"
            ENERGY_CHART -> "Energy Level"
            STRESS_CHART -> "Stress Level"
            DETAILED_CHART -> "Detailed"
        }

    fun getColor(): Color =
        when (this) {
            SLEEP_SCORE_CHART -> ghostTint
            DURATION_CHART -> yellowTint
            ENERGY_CHART -> cyanTint
            STRESS_CHART -> blushTint
            DETAILED_CHART -> ghostTint
        }
}
