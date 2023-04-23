package com.example.helloworld.model

import java.io.Serializable

class CalculatorResult(): Serializable {

    var result: String = ""
    var time: String = ""
    constructor(result: String, time: String) : this() {
        this.result = result
        this.time = time
    }
}