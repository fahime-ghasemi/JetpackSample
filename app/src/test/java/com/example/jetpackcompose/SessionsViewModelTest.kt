package com.example.jetpackcompose

import com.example.jetpackcompose.domain.interactor.GetSessionListUseCase
import com.example.jetpackcompose.domain.interactor.SearchSessionListUseCase
import com.example.jetpackcompose.presentation.viewmodel.SessionsViewModel

class SessionsViewModelTest {
    private val sessionRepository = TestSessionRepository()
    private val getSessionListUseCase = GetSessionListUseCase(sessionRepository)
    private val searchSessionListUseCase = SearchSessionListUseCase(sessionRepository)
    private val viewModel = SessionsViewModel(getSessionListUseCase,searchSessionListUseCase)

//    @Test
//    private fun
}