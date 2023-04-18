package com.example.mobile_labs

class Person {
    val dependentCost: Int = 11000000
    var salary: Double = 0.0
        get() = field
        set(value){
            field = value
        }
    var name: String = ""
        get() = field
        set(value){
            field = value
        }
    fun calGrossSalary():Double{
        var gross = 0
        if(salary != null){
            gross = salary.toInt()
        }
        var temp: Double = gross * (1-0.15)
        if(temp <= dependentCost) return temp
        return dependentCost + (temp - dependentCost) * (1 - 0.15)
    }
    fun printInfo(): String{
        return "${name}\n${salary}"
    }
}