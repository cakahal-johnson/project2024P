// Creating a pair
val equipment = "fish net" to "catching fish"
println("${equipment.first} used for ${equipment.second}")

// Creating a triple
val numbers = Triple(6, 9, 42)
println(numbers.toString())
println(numbers.toList())

// Nested pair
val equipment2 = ("fish net" to "catching fish") to "equipment"
println("${equipment2.first} is ${equipment2.second}")
println("${equipment2.first.second}")
