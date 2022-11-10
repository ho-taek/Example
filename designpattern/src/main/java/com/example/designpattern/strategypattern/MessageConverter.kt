package com.example.designpattern.strategypattern

class MessageConverter(private var messageGenerateStrategy : MessageGenerateStrategy) {
    fun convert(string : String) : String{
        return messageGenerateStrategy.process(string)
    }

    fun setStrategy(messageGenerateStrategy: MessageGenerateStrategy){
        this.messageGenerateStrategy = messageGenerateStrategy
    }
}