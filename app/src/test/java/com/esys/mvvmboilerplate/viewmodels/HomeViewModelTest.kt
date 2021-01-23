package com.esys.mvvmboilerplate.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.esys.mvvmboilerplate.MainCoroutineScopeRule
import com.esys.mvvmboilerplate.data.repository.AuthRepository
import com.esys.mvvmboilerplate.modules.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Test
    fun `try loading `() {
        val viewModel = HomeViewModel(authRepository)
        coroutineScopeRule.runBlockingTest {
            viewModel.getLoading().observeForever(loadingObserver)
            viewModel.tryShowLoading()
        }

        verify(loadingObserver).onChanged(true) //Show Loading
        verify(loadingObserver).onChanged(false) //Dismiss Loading
    }
}