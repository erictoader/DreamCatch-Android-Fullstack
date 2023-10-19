package ps.erictoader.ui.feature.home.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.ui.feature.home.view.chart.ChartFactory
import ps.erictoader.ui.feature.home.view.chart.ChartNegativeTrendDecorator
import ps.erictoader.ui.feature.home.view.chart.ChartPositiveTrendDecorator
import ps.erictoader.ui.feature.home.view.chart.ChartType
import ps.erictoader.ui.feature.home.view.style.HomeScreenColors
import ps.erictoader.ui.feature.home.view.style.HomeScreenDimens
import kotlin.random.Random

private val dimens = HomeScreenDimens
private val colors = HomeScreenColors

@Composable
fun ChartModule(
    module: DreamModule
) {
    val chartTypeState = remember { mutableStateOf(ChartType.SLEEP_SCORE_CHART) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = module.tag,
                color = colors.text,
                fontSize = dimens.header
            )

            ChartTypeSelector(chartTypeState)
        }

        if (module.items.map { it.sleepScore }.average() > 3.5) {
            ChartFactory.Draw(
                strategy = ChartPositiveTrendDecorator(chartTypeState.value.getStrategy()),
                dataset = module.items
            )
        } else {
            ChartFactory.Draw(
                strategy = ChartNegativeTrendDecorator(chartTypeState.value.getStrategy()),
                dataset = module.items
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChartTypeSelector(
    chartTypeState: MutableState<ChartType>
) {
    val chartTypes = remember { ChartType.values() }
    var expanded by remember { mutableStateOf(false) }
    val contentColor by animateColorAsState(
        targetValue = chartTypeState.value.getColor(),
        animationSpec = tween(500)
    )

    Row(
        horizontalArrangement = Arrangement.End
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { }
        ) {
            Row(
                modifier = Modifier.clickable { expanded = !expanded },
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = chartTypeState.value.getChartName(),
                    color = colors.ghostTint
                )
                Icon(
                    modifier = Modifier.rotate(if (expanded) 180f else 360f),
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Trailing icon for exposed dropdown menu",
                    tint = contentColor
                )
            }

            ExposedDropdownMenu(
                modifier = Modifier
                    .defaultMinSize(minWidth = 150.dp)
                    .background(colors.ghostTint),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                chartTypes.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier.background(colors.ghostTint),
                        onClick = {
                            chartTypeState.value = item
                            expanded = false
                        }
                    ) {
                        Text(
                            text = item.getChartName(),
                            color = colors.navyTint
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_ChartModule() {
    ChartModule(
        DreamModule(
            tag = "Recent sleep",
            items = generateMockEntries()
        )
    )
}

private fun generateMockEntries(): List<Dream> =
    List(30) {
        Dream(
            description = "Description $it",
            sleepDuration = Random(it).nextFloat() * 5f,
            energyLevel = Random(it).nextFloat() * 4f,
            stress = Random(it).nextFloat() * 3f,
            sleepScore = Random(it).nextFloat() * 5f,
            entryDate = 0L
        )
    }
