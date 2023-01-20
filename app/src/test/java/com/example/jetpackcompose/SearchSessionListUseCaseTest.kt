package com.example.jetpackcompose

import com.example.jetpackcompose.data.dto.TrackDto
import com.example.jetpackcompose.domain.interactor.SearchSessionListUseCase
import com.example.jetpackcompose.domain.model.Session
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchSessionListUseCaseTest {

    private val sessionRepository = TestSessionRepository()
    private val useCase = SearchSessionListUseCase(sessionRepository)

    @Before
    fun prepareRepository()
    {
        //Arrange
        sessionRepository.addSession(Session("sara",2, emptyList(), TrackDto("track1","")))
        sessionRepository.addSession(Session("fahime",2, emptyList(), TrackDto("track2","")))
    }

    @Test
    fun return_emptyList_when_page_is_negative() = runTest {
        val result = useCase(-1,"")
        val result2 = useCase(-1,"fahime")

        assertEquals(result, emptyFlow<Session>())
        assertEquals(result2, emptyFlow<Session>())

    }

    @Test
    fun return_notEmptyList_when_query_is_match() = runTest {
        val result = useCase(0,"sara")
        assertEquals(result.toList().size, 1)
    }
}