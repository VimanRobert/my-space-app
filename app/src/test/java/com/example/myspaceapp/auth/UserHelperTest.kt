package com.example.myspaceapp.auth

import android.content.Context
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UserHelperTest {
    private lateinit var userHelper: UserHelper
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = mockk(relaxed = true)
        userHelper = UserHelper(context)
    }

    @After
    fun tearDown() = unmockkAll()


    @Test
    fun `password should match the confirmation password`() {
        // given
        val password = "Android_2024"
        val confirmPassword = "Android_2024"

        // when
        val check = userHelper.confirmPassword(password, confirmPassword)

        // then
        assertTrue(check)
    }

    @Test
    fun `password should not match the confirmation password`() {
        // given
        val password = "Android_2024"
        val confirmPassword = "Android_0000"

        // when
        val check = userHelper.confirmPassword(password, confirmPassword)

        // then
        assertFalse(check)
    }
}