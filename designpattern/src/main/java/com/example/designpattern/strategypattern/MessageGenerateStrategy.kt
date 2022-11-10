package com.example.designpattern.strategypattern

sealed class MessageGenerateStrategy{

    abstract fun process(string : String) : String

    data class AttachHeader(val header : String) : MessageGenerateStrategy(){
        override fun process(string: String): String {
            return "$header $string"
        }
    }

    object UpperCase : MessageGenerateStrategy(){
        override fun process(string: String): String {
            return string.uppercase()
        }
    }

    data class Etc(val strategy: (String) -> String) : MessageGenerateStrategy(){
        override fun process(string: String): String {
            return strategy(string)
        }
    }
}
