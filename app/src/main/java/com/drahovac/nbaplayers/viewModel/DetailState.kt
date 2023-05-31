package com.drahovac.nbaplayers.viewModel

/**
 * A sealed interface representing the state of a detail screen.
 *
 * @param T The type of the detail.
 */
sealed interface DetailState<out T> {

    /**
     * The state indicates that the detail is loading.
     */
    object LOADING : DetailState<Nothing>

    /**
     * The state indicates that an error occurred while loading the detail.
     *
     * @param error The error that occurred.
     */
    data class Error(val error: Throwable) : DetailState<Nothing>

    /**
     * The state indicates that the detail was loaded successfully.
     *
     * @param detail The detail data.
     * @param imageUrl The URL of the team's image.
     */
    data class Success<T>(
        val detail: T,
        val imageUrl: String,
    ) : DetailState<T>
}
