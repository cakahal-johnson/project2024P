class MyClass {
    companion object Factory {
        fun createInstance(): MyClass = MyClass()
    }
}

val instance = MyClass.Factory.createInstance()
