package ps.erictoader.ui.feature.newentry.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.ui.feature.common.view.PopupScreen
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations
import ps.erictoader.ui.feature.newentry.viewmodel.NewEntryViewModel

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors
private val translations = NewEntryScreenTranslations

@Composable
fun NewEntryMainScreen(
    viewModel: NewEntryViewModel = koinViewModel(),
    onNext: (String, List<String>) -> Unit
) {
    var showTagPicker by remember { mutableStateOf(false) }
    var showAddTagDialog by remember { mutableStateOf(false) }
    var showRemoveTagDialog by remember { mutableStateOf(false) }
    var tagToRemove by remember { mutableStateOf<Tag?>(null) }

    Column(
        modifier = Modifier
            .padding(
                vertical = dimens.verticalMargin,
                horizontal = dimens.horizontalMargin
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.spacing)
    ) {
        var description by remember { mutableStateOf("") }
        val selectedTags = remember { mutableStateListOf<String>() }

        Spacer(modifier = Modifier.size(dimens.topBottomSpacing))

        Text(
            text = translations.GOOD_MORNING,
            color = colors.titleText,
            fontSize = dimens.title,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = translations.HOW_WAS_SLEEP,
                color = colors.ghost,
                fontSize = dimens.subtitle
            )

            Spacer(modifier = Modifier.height(dimens.textFieldSpacing))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                minLines = dimens.TEXT_FIELD_MIN_LINES,
                maxLines = dimens.TEXT_FIELD_MAX_LINES,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colors.ghost
                ),
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(.5f),
                        text = translations.WRITE_SHORT_DESCRIPTION,
                        color = colors.ghost
                    )
                }
            )
        }

        if (showTagPicker) {
            TagPicker(
                tags = viewModel.tagList,
                onTagSelected = { tag, selected ->
                    if (selected) selectedTags.add(tag.name)
                    else selectedTags.remove(tag.name)
                },
                onAddTagClicked = {
                    showAddTagDialog = true
                },
                onRemoveTagClicked = {
                    tagToRemove = it
                    showRemoveTagDialog = true
                }
            )
        }

        NextButton(
            width = dimens.buttonWidth,
            height = dimens.buttonHeight,
            onClick = { onNext(description, selectedTags) },
            isEnabled = description.isNotBlank()
        )

        Spacer(modifier = Modifier.size(dimens.topBottomSpacing))
    }

    if (showAddTagDialog) {
        AddTagDialog(
            onAddClicked = {
                viewModel.addNewTag(it)
            },
            onDismissRequest = {
                showAddTagDialog = false
            }
        )
    } else if (showRemoveTagDialog) {
        tagToRemove?.let { tag ->
            RemoveTagDialog(
                tag = tag,
                onRemoveClicked = {
                    viewModel.removeTag(it)
                    tagToRemove = null
                },
                onDismissRequest = {
                    showRemoveTagDialog = false
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllTags()
    }

    when (viewModel.state.value) {
        is NewEntryViewModel.State.TagsLoaded -> {
            showTagPicker = true
        }
        is NewEntryViewModel.State.EntryAdded -> {
        }
        is NewEntryViewModel.State.Error -> {
        }
        is NewEntryViewModel.State.Loading -> {
        }
        is NewEntryViewModel.State.TagAdded -> {
        }
        is NewEntryViewModel.State.TagExists -> {
        }
        is NewEntryViewModel.State.TagRemoved -> {
        }
    }
}

@Composable
private fun TagPicker(
    tags: List<Tag>,
    onTagSelected: (Tag, Boolean) -> Unit,
    onAddTagClicked: () -> Unit,
    onRemoveTagClicked: (Tag) -> Unit
) {
    val tagFocusMap = remember {
        mutableStateMapOf(
            *tags
                .filter { !it.isEditable }
                .mapIndexed { index, elem -> elem to (index == 0) }
                .toTypedArray()
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(10),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        mutuallyExclusiveTags(
            tags = tags.filter { !it.isEditable },
            focusMap = tagFocusMap,
            onTagSelected = onTagSelected
        )

        items(
            items = tags.filter { it.isEditable },
            span = { adaptiveSpan(it) }
        ) { tag ->
            var selected by remember { mutableStateOf(false) }

            TagCard(
                tag = tag,
                focused = selected,
                onClick = {
                    selected = !selected
                    onTagSelected(tag, selected)
                },
                onDelete = { onRemoveTagClicked(tag) }
            )
        }

        item(
            span = { GridItemSpan(5) }
        ) {
            CreateTagCard(
                onClick = onAddTagClicked
            )
        }
    }

    LaunchedEffect(Unit) {
        onTagSelected(tags.first(), true)
    }
}

private fun LazyGridScope.mutuallyExclusiveTags(
    tags: List<Tag>,
    focusMap: MutableMap<Tag, Boolean>,
    onTagSelected: (Tag, Boolean) -> Unit
) {
    fun reassignUniqueFocus(focusedTag: Tag) =
        focusMap.keys.forEach { focusMap[it] = it == focusedTag }

    items(
        items = tags,
        span = { adaptiveSpan(it) }
    ) { tag ->
        TagCard(
            tag = tag,
            focused = focusMap[tag] ?: false,
            onClick = {
                reassignUniqueFocus(tag)
                tags.filter { it != tag }
                    .forEach {
                        onTagSelected(it, false)
                    }
                onTagSelected(tag, true)
            }
        )
    }
}

private fun adaptiveSpan(tag: Tag): GridItemSpan {
    var span = tag.name.length / 2
    if (tag.isEditable) span += 1
    if (span > 10) span = 10
    return GridItemSpan(span)
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_NewEntryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    PopupScreen {
        NewEntryMainScreen(
            onNext = { _, _ -> }
        )
    }
}
