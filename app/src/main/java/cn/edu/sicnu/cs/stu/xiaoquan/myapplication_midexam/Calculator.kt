package cn.edu.sicnu.cs.stu.xiaoquan.myapplication_midexam

class Calculator {
    fun add(a: Double, b: Double): Double {
        return a + b
    }

    fun sub(a: Double, b: Double): Double {
        return a - b
    }

    fun mul(a: Double, b: Double): Double {
        return a * b
    }

    fun div(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw ArithmeticException("除数不能为 0")
        }
        return a / b
    }
}
