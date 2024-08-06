package com.example.practical2

//1.	Companion Object Example
class Choice {
    companion object {
        var name: String = "lyric"
        fun showDescription(name: String) = println("My favorite $name")
    }
}


fun main() {
//    Companion Object
    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")



    // Create a pair and Triples Example
    val equipment = "fish net" to "catching fish"
    println("${equipment.first} used for ${equipment.second}")


    // Create a triple
    val numbers = Triple(6, 9, 42)
    println(numbers.toString())
    println(numbers.toList())

    // Create a pair where the first part is itself a pair
    val equipment2 = ("fish net" to "catching fish") to "equipment"
    println("${equipment2.first} is ${equipment2.second}")
    println("${equipment2.first.second}")

}
