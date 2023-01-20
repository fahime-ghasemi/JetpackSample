package com.example.jetpackcompose.domain.interactor

import com.example.jetpackcompose.domain.model.repository.SessionRepository
import kotlinx.coroutines.flow.emptyFlow

class SearchSessionListUseCase(private val sessionRepository: SessionRepository) {
    /**
     * For having a return type instead of Unit in invoke operator you should use = for writing method's body
     */
    suspend operator fun invoke(page: Int, query: String) =
        if (page < 0) emptyFlow() else sessionRepository.search(page, query)
}