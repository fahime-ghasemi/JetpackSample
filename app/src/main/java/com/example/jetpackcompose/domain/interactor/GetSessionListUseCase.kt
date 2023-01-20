package com.example.jetpackcompose.domain.interactor

import com.example.jetpackcompose.domain.model.repository.SessionRepository
import kotlinx.coroutines.flow.emptyFlow

class GetSessionListUseCase(private val sessionRepository: SessionRepository) {
    suspend operator fun invoke(page: Int) =
        if (page < 0) emptyFlow() else sessionRepository.getSessions(page)

}