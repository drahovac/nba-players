package com.drahovac.nbaplayers.viewModel

import com.drahovac.nbaplayers.data.PlayerDetailRepository
import com.drahovac.nbaplayers.domain.PlayerDetail
import com.drahovac.nbaplayers.domain.Team
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

class PlayerDetailViewModelTest {


    private val repository: PlayerDetailRepository = mockk()
    private lateinit var viewModel: PlayerDetailViewModel


    @Test
    fun `set initial loading state`() = runTest {
        setUpViewModel(dispatcher = StandardTestDispatcher())

        assertEquals(DetailState.LOADING, viewModel.state.value)
    }

    @Test
    fun `set success state on repository result success`() {
        setUpViewModel()

        assertTrue(viewModel.state.value is DetailState.Success)
        assertEquals(PLAYER_DETAIL, (viewModel.state.value as DetailState.Success).detail)
    }

    @Test
    fun `set error state on repository result error`() {
        val error = Throwable("test error")
        setUpViewModel(Result.failure(error))

        assertTrue(viewModel.state.value is DetailState.Error)
        assertEquals(error, (viewModel.state.value as DetailState.Error).error)
    }

    @Test
    fun `retry fetching data`() {
        setUpViewModel()

        viewModel.retry()

        coVerify(exactly = 2) { repository.getPlayerDetail(PLAYER_ID) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setUpViewModel(
        result: Result<PlayerDetail> = Result.success(PLAYER_DETAIL),
        dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) {
        Dispatchers.setMain(dispatcher)
        coEvery { repository.getPlayerDetail(PLAYER_ID) } returns result

        viewModel = PlayerDetailViewModel(PLAYER_ID, repository)
    }

    private companion object {
        const val PLAYER_ID = "3"
        val PLAYER_DETAIL = PlayerDetail(
            id = PLAYER_ID,
            firstName = "First",
            lastName = "Last",
            position = "F",
            team = Team("", ""),
            heightFeet = 3,
            heightInches = 2,
            weightPounds = 1,
        )
    }
}