package com.esys.mvvmboilerplate.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.esys.mvvmboilerplate.MainCoroutineScopeRule
import com.esys.mvvmboilerplate.data.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()
}