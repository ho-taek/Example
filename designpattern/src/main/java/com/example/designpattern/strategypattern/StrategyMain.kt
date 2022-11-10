package com.example.designpattern.strategypattern

fun main(){
    val message = "hello"
    val fromIOSStrategy = MessageGenerateStrategy.Etc { message : String -> "${message} : Written from mac"}
    var messageConverter = MessageConverter(fromIOSStrategy)

    println(messageConverter.convert(message))

    val messageHeaderStrategy = MessageGenerateStrategy.AttachHeader("hihi")
    messageConverter.setStrategy(messageHeaderStrategy)

    println(messageConverter.convert(message))

    messageConverter.setStrategy(MessageGenerateStrategy.UpperCase)
    println(messageConverter.convert(message))


}