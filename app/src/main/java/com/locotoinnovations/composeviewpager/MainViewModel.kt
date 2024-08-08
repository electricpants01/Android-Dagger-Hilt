package com.locotoinnovations.composeviewpager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locotoinnovations.composeviewpager.network.PostService
import com.locotoinnovations.composeviewpager.network.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * In this case, we're not calling the service in the repository, but it's a good practice to do so.
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val postService: PostService,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState: Flow<MainActivityUiState> = _uiState

    /**
     * This will be fired when the user taps on the button
     */
    fun getData() {
        viewModelScope.launch {
            /**
             * it's important to make this call `postService.getPosts()` in the IO thread
             */
            val posts: List<Post> = withContext(Dispatchers.IO) {
                postService.getPosts()
            }

            _uiState.update {
                MainActivityUiState(
                    posts = posts,
                    isLoading = false,
                )
            }
        }
    }
}

/**
 * This is the state of the UI
 */
data class MainActivityUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false
)