class MyClass(val name: String) {
    companion object {
        // A property inside the companion object
        const val defaultName = "DefaultName"

        // A function inside the companion object
        fun greet() {
            println("Hello from the companion object!")
        }
    }
}

fun main() {
    println(MyClass.defaultName) // Accessing the property
    MyClass.greet() // Calling the function
}
