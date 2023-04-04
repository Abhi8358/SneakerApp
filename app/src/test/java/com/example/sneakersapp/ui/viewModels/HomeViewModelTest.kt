package com.example.sneakersapp.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sneakersapp.repository.FakeHomeRepository
import com.example.sneakersapp.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestDispatcher
        get() = StandardTestDispatcher()


    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        homeViewModel = HomeViewModel(FakeHomeRepository())
    }

    @Test
    fun verifyFetchedSneakers() = runBlocking{
        //homeViewModel.getSneakers()
        testDispatcher.scheduler.advanceUntilIdle()
        val sneakersList = homeViewModel.sneakerLiveData.getOrAwaitValue(time = 10)

        assertNotNull(sneakersList)
    }

    @After
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
