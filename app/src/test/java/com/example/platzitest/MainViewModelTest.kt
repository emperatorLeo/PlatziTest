package com.example.platzitest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.platzitest.domain.dtos.SoundDto
import com.example.platzitest.domain.usecase.DeleteUseCase
import com.example.platzitest.domain.usecase.InsertUseCase
import com.example.platzitest.domain.usecase.ReadUseCase
import com.example.platzitest.domain.usecase.SearchUseCase
import com.example.platzitest.domain.usecase.UpdateUseCase
import com.example.platzitest.presentation.state.UiState
import com.example.platzitest.presentation.viewmodel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @RelaxedMockK
    private lateinit var readUseCase: ReadUseCase

    @RelaxedMockK
    private lateinit var searchUseCase: SearchUseCase

    @RelaxedMockK
    private lateinit var updateUseCase: UpdateUseCase

    @RelaxedMockK
    private lateinit var deleteUseCase: DeleteUseCase

    @RelaxedMockK
    private lateinit var insertUseCase: InsertUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel =
            MainViewModel(
                readUseCase,
                searchUseCase,
                updateUseCase,
                deleteUseCase,
                insertUseCase
            )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel invoke searchUseCase and is success, uiState should be success`() =
        runTest {
            // GIVEN
            val mockedList = listOf<SoundDto>()
            val mockedFlow = flow<Response<List<SoundDto>>> { emit(Response.success(mockedList)) }
            coEvery { searchUseCase.invoke(any()) } returns mockedFlow

            // WHEN
            viewModel.searchSound("piano")

            // THEN
            assertEquals(UiState.Success(mockedList), viewModel.uiState.value)
        }
}