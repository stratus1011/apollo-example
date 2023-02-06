package com.example.feature1

import com.example.servicea.fragment.ScreenFragment

class Hello {
    fun hi(text: String): String {
        return "Hello $text"
    }
}

fun ScreenFragment.transform(): String {
    return this.screen.__typename
}
