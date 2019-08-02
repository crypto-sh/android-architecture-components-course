package com.laitec.mvvm

typealias callback = (String,Int,Double) -> Unit

class test {

    fun kotlinSampleCode(){
        var age = 32
        var name  = "name : Ali, age : $age"
        when(age){
            in 0..10 -> { }
            10 -> {}
            is Int -> { }
            else -> {
            }
        }
        var test : List<Int> = ArrayList<Int>()
        val items = arrayOf(1,2,3,4,5,6,7,8)
        for (item in items  ){
        }
        if (age > 20) {
        }else {
        }
        for (item in items){}
        for ((index,item) in items.withIndex()){
        }
        callBacketest{ _, _, _ ->
        }
    }
    public fun sum2(a : Int, b : Int) : Int = a + b
    companion object {
        const val name = "Ali"
        fun check(){}
    }
    class callBacketest(val callback : callback) {
        fun call(){
            callback("",0,0.0)
        }
    }
    data class Model(val data : String)
}
