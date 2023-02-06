package com.example.feature1

import com.example.servicea.fragment.ScreenFragment
import org.junit.Assert.assertEquals
import org.junit.Test

internal class HelloTest {

    @Test
    fun `this is a test`() {
        val hello = Hello()
        assertEquals("Hello Cody", hello.hi("Cody"))
    }

    @Test
    fun `test a transform`() {
        val data = ScreenFragment(
            screen = ScreenFragment.Screen(
                __typename = "hi",
                resolution = "100",
                onScreen = ScreenFragment.OnScreen(true)
            )
        )

        assertEquals("hi", data.transform())
    }
}
