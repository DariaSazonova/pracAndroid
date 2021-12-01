package com.example.pr9

//Table
interface Table {
    var tab:MutableList<DataRow>
    fun getList() : List<DataRow>
    fun add(dataRow: DataRow)
    fun set(index : Int, dataRow: DataRow)
    fun delete(index: Int)
}

interface Column {
    val name : String
    fun sort(list : List<DataRow>) : List<DataRow>
    fun search(list : List<DataRow>, input:String) : List<DataRow>
}


