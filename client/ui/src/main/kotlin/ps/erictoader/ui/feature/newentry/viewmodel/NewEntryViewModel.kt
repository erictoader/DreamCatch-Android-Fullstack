package ps.erictoader.ui.feature.newentry.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineExceptionHandler
import ps.erictoader.domain.feature.newentry.exception.TagExistsException
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.domain.feature.newentry.usecase.abstraction.AddNewEntryUseCase
import ps.erictoader.domain.feature.newentry.usecase.abstraction.AddNewTagUseCase
import ps.erictoader.domain.feature.newentry.usecase.abstraction.GetAllTagsUseCase
import ps.erictoader.domain.feature.newentry.usecase.abstraction.RemoveTagUseCase
import ps.erictoader.ui.feature.common.util.DispatcherProvider
import ps.erictoader.ui.feature.common.viewmodel.BaseViewModel

class NewEntryViewModel(
    dispatcherProvider: DispatcherProvider,
    private val addNewEntryUseCase: AddNewEntryUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
    private val addNewTagUseCase: AddNewTagUseCase,
    private val removeTagUseCase: RemoveTagUseCase
) : BaseViewModel(dispatcherProvider) {

    sealed class State {
        object Loading : State()
        object TagsLoaded : State()
        data class TagExists(val tag: String) : State()
        object TagAdded : State()
        object TagRemoved : State()
        object EntryAdded : State()
        object Error : State()
    }

    var state = mutableStateOf<State>(State.Loading)
        private set

    var tagList = mutableStateListOf<Tag>()
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")

        if (exception is TagExistsException)
            state.value = State.TagExists(exception.existingTagName)
        else state.value = State.Error
    }

    fun getAllTags() =
        launchOnIO(exceptionHandler) {
            val tags = getAllTagsUseCase()
            state.value = State.TagsLoaded
            tagList.addAll(tags)
        }

    fun addNewTag(tag: String) =
        launchOnIO(exceptionHandler) {
            val newTag = addNewTagUseCase(tag)
            state.value = State.TagAdded
            tagList.add(newTag)
        }

    fun removeTag(tag: Tag) =
        launchOnIO(exceptionHandler) {
            removeTagUseCase(tag)
            state.value = State.TagRemoved
            tagList.remove(tag)
        }

    fun addNewEntry(
        description: String,
        tags: List<String>,
        duration: Float,
        energy: Float,
        stress: Float
    ) = launchOnIO(exceptionHandler) {
        val newEntry = NewDreamEntry.fromRawData(
            description,
            tags,
            duration,
            energy,
            stress
        )
        addNewEntryUseCase(newEntry)
        state.value = State.EntryAdded
    }

    private companion object {
        private val TAG = NewEntryViewModel::class.java.simpleName
    }
}
