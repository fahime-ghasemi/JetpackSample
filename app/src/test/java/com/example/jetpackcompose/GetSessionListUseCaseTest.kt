package com.example.jetpackcompose

import com.example.jetpackcompose.data.dto.TrackDto
import com.example.jetpackcompose.domain.interactor.GetSessionListUseCase
import com.example.jetpackcompose.domain.model.Session
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class GetSessionListUseCaseTest {

    private val sessionRepository = TestSessionRepository()
    private val useCase = GetSessionListUseCase(sessionRepository)

    @Before
    fun prepareRepository()
    {
        //Arrange
        sessionRepository.addSession(Session("sara",2, emptyList(), TrackDto("track1","")))
        sessionRepository.addSession(Session("fahime",2, emptyList(), TrackDto("track2","")))
    }
    @Test
    fun return_emptyList_when_page_is_negative() = runTest {
        val result = useCase(-1)

        assertEquals(result, emptyFlow<Session>())

    }

    @Test
    fun return_notEmptyList_when_page_is_positive() = runTest {
        val result = useCase(0)
        assertNotEquals(result.toList().size, 0)

        val result2 = useCase(2)
        assertNotEquals(result2.toList().size, 0)

    }
}