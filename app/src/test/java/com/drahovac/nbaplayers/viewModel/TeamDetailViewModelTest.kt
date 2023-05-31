package com.drahovac.nbaplayers.viewModel

import com.drahovac.nbaplayers.data.TeamDetailRepository
import com.drahovac.nbaplayers.domain.TeamDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

class TeamDetailViewModelTest {

    private val repository: TeamDetailRepository = mockk()
    private lateinit var viewModel: TeamDetailViewModel

    @Test
    fun `set initial loading state`() = runTest {
        setUpViewModel(dispatcher = StandardTestDispatcher())

        assertEquals(DetailState.LOADING, viewModel.state.value)
    }

    @Test
    fun `set success state on repository result success`() {
        setUpViewModel()

        TestCase.assertTrue(viewModel.state.value is DetailState.Success)
        assertEquals(TEAM_DETAIL, (viewModel.state.value as DetailState.Success).detail)
    }

    @Test
    fun `set error state on repository result error`() {
        val error = Throwable("test error")
        setUpViewModel(Result.failure(error))

        TestCase.assertTrue(viewModel.state.value is DetailState.Error)
        assertEquals(error, (viewModel.state.value as DetailState.Error).error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setUpViewModel(
        result: Result<TeamDetail> = Result.success(TEAM_DETAIL),
        dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) {
        Dispatchers.setMain(dispatcher)
        coEvery { repository.getTeam(TEAM_ID) } returns result

        viewModel = TeamDetailViewModel(TEAM_ID, repository)
    }

    @Test
    fun `retry fetching data`() {
        setUpViewModel()

        viewModel.retry()

        coVerify(exactly = 2) { repository.getTeam(TEAM_ID) }
    }


    private companion object {
        const val TEAM_ID = "45"
        val TEAM_DETAIL = TeamDetail(
            id = TEAM_ID,
            fullName = "fullName",
            name = "name",
            abbreviation = "abb",
            city = "city",
            conference = "conf",
            division = "div"
        )
    }
}