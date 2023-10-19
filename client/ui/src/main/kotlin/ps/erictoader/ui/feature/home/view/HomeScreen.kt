package ps.erictoader.ui.feature.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import org.koin.androidx.compose.koinViewModel
import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.ui.ScreenDestination
import ps.erictoader.ui.feature.common.view.ErrorIndicator
import ps.erictoader.ui.feature.common.view.FullScreen
import ps.erictoader.ui.feature.common.view.LoadingIndicator
import ps.erictoader.ui.feature.home.view.style.HomeScreenColors
import ps.erictoader.ui.feature.home.view.style.HomeScreenDimens
import ps.erictoader.ui.feature.home.view.style.HomeScreenTranslations
import ps.erictoader.ui.feature.home.viewmodel.HomeViewModel
import ps.erictoader.ui.navigate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale

private val dimens = HomeScreenDimens
private val colors = HomeScreenColors
private val translations = HomeScreenTranslations

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenDestination.NewEntryScreen) },
                backgroundColor = colors.ghostTint,
                contentColor = colors.navyTint,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = dimens.floatingButtonElevation
                ),
                content = {
                    Icon(
                        modifier = Modifier.size(dimens.floatingButtonIconSize),
                        painter = rememberVectorPainter(image = Icons.Default.Add),
                        contentDescription = null
                    )
                }
            )
        },
        content = { padding ->
            HomeScreenContent(
                contentPadding = padding
            )
        }
    )
}

@Composable
private fun HomeScreenContent(
    contentPadding: PaddingValues,
    viewModel: HomeViewModel = koinViewModel()
) {
    val timelineState = remember { mutableStateOf(LocalDate.now().getMonthInterval()) }

    FullScreen(modifier = Modifier.padding(contentPadding)) {
        LazyColumn(
            modifier = Modifier
                .padding(
                    vertical = dimens.verticalMargin,
                    horizontal = dimens.horizontalMargin
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimens.spacing)
        ) {
            item {
                // TODO: Replace User with registered name in the future
                Text(
                    text = translations.getGreetingByTime() + ", User",
                    color = colors.text,
                    fontSize = dimens.title,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                TimelineSelector(timelineState)
            }

            when (val state = viewModel.state.value) {
                is HomeViewModel.State.ModulesLoaded -> {
                    modulesOrEmptyIndicator(state.modules, timelineState.value)
                }

                is HomeViewModel.State.Loading -> {
                    item { LoadingIndicator() }
                }

                is HomeViewModel.State.Error -> {
                    item { ErrorIndicator() }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadModules()
    }
}

private const val MONTH_TIMELINE = 0
private const val WEEK_TIMELINE = 1

@Composable
private fun TimelineSelector(
    timelineState: MutableState<Pair<LocalDate, LocalDate>>
) {
    val calendarState = rememberUseCaseState(false)
    val timelineTypeState = remember { mutableStateOf(MONTH_TIMELINE) }
    val selectedTimeline = getTimelineTranslation(timelineState.value, timelineTypeState.value)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Showing results for:",
            color = colors.text,
            fontSize = dimens.header,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.clickable { calendarState.show() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = selectedTimeline,
                color = colors.text,
                fontSize = dimens.header,
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = if (timelineTypeState.value == WEEK_TIMELINE)
                        Icons.Default.CalendarViewWeek
                    else
                        Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = colors.ghostTint
                )
                Text(
                    text = if (timelineTypeState.value == WEEK_TIMELINE)
                        "Period"
                    else
                        "Month",
                    color = colors.ghostTint
                )
            }
        }
    }

    DialogTimelinePicker(
        calendarState = calendarState,
        timelineTypeState = timelineTypeState,
        timelineState = timelineState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DialogTimelinePicker(
    calendarState: UseCaseState,
    timelineTypeState: MutableState<Int>,
    timelineState: MutableState<Pair<LocalDate, LocalDate>>
) {
    DialogBase(
        state = calendarState
    ) {
        val calendarSelection = if (timelineTypeState.value == MONTH_TIMELINE)
            CalendarSelection.Date { date ->
                timelineState.value = date.getMonthInterval()
            }
        else
            CalendarSelection.Period { startDate, endDate ->
                timelineState.value = Pair(
                    startDate,
                    endDate
                )
            }
        val calendarConfig = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        )
        var expanded by remember { mutableStateOf(false) }
        val selectorTypes = remember { listOf("Month", "Period") }
        var selectedType by remember { mutableStateOf(selectorTypes[timelineTypeState.value]) }
        Column(horizontalAlignment = Alignment.End) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Selection type:",
                    fontSize = 18.sp
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { }
                ) {
                    Row(
                        modifier = Modifier.clickable { expanded = !expanded },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedType,
                            color = colors.navyTint,
                            fontSize = 18.sp
                        )
                        Icon(
                            modifier = Modifier.rotate(if (expanded) 180f else 360f),
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Trailing icon for exposed dropdown menu",
                            tint = colors.navyTint
                        )
                    }

                    ExposedDropdownMenu(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 150.dp)
                            .background(colors.ghostTint),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        selectorTypes.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                modifier = Modifier.background(colors.ghostTint),
                                onClick = {
                                    timelineTypeState.value = index
                                    selectedType = item
                                    expanded = false
                                }
                            ) {
                                Text(
                                    text = item,
                                    color = colors.navyTint
                                )
                            }
                        }
                    }
                }
            }

            CalendarView(
                useCaseState = calendarState,
                config = calendarConfig,
                selection = calendarSelection,
            )
        }
    }
}

private fun LocalDate.getMonthInterval(): Pair<LocalDate, LocalDate> =
    Pair(
        this.withDayOfMonth(1),
        this.withDayOfMonth(this.lengthOfMonth())
    )

private fun getTimelineTranslation(timeline: Pair<LocalDate, LocalDate>, type: Int): String {
    val monthAbbreviation = timeline.first.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    return if (type == MONTH_TIMELINE) "$monthAbbreviation ${timeline.first.year}"
    else "${timeline.first.dayOfMonth} - ${timeline.second.dayOfMonth} $monthAbbreviation"
}

private fun LazyListScope.modulesOrEmptyIndicator(
    modules: List<DreamModule>,
    timeline: Pair<LocalDate, LocalDate>
) {
    val shownModules = modules.map { dreamModule ->
        val filteredDreams = dreamModule.items.filter { dream ->
            dream.isTimestampInTimeline(timeline)
        }
        dreamModule.copy(items = filteredDreams)
    }.filter { it.items.isNotEmpty() }

    if (shownModules.isEmpty()) {
        item { NoEntriesIndicator() }
    } else {
        items(shownModules) { module ->
            ChartModule(module)
        }
    }
}

private fun LocalDate.toUnixTimestamp(): Long {
    val startOfDay: LocalDateTime = this.atStartOfDay()
    val zoneId: ZoneId = ZoneId.systemDefault()
    val zonedDateTime: ZonedDateTime = startOfDay.atZone(zoneId)
    return zonedDateTime.toEpochSecond()
}

private fun Dream.isTimestampInTimeline(timeline: Pair<LocalDate, LocalDate>) =
    (this.entryDate > timeline.first.toUnixTimestamp()) and
            (this.entryDate < timeline.second.toUnixTimestamp())

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_HomeScreen() {
    HomeScreen()
}
