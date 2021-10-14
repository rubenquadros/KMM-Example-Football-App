package com.ruben.data

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}