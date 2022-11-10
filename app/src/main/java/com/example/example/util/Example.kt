package com.example.example.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val n = bf.readLine().toInt()
    var set = HashSet<Int>()

    repeat(n){
        val command = bf.readLine().split(" ")
        println(command)
        var num = 0
        if(command[0] != "all" && command[0] != "empty")
            num = command[1].toInt()

        when(command[0]){
            "add" -> {
                set.add(num)
            }
            "remove" -> {
                if(set.contains(num)) set.remove(num)
            }
            "check" -> {
                println(set.contains(num))
            }
            "toggle" -> {
                if(set.contains(num)) set.remove(num) else set.add(num)
            }
            "all" -> {
                for(i in 1..20){
                    if(!set.contains(i)) set.add(i)
                }
            }
            "empty" -> {
                set.clear()
            }
        }
    }
}
