package com.example.pr9

import android.graphics.Bitmap


data class DataRow(
    var cinema: String,
    var film: String,
    var hall: Int,
    var date: String,
    var time: String,
    var photo:Bitmap
)

data class NullableDataRow(
    var cinema: String? = null,
    var film: String? = null,
    var hall: Int? = null,
    var date: String? = null,
    var time: String? = null,
    var photo:Bitmap? = null
) {
    fun asDataRow() : DataRow = DataRow(cinema!!, film!!, hall!!, date!!, time!!, photo!!)
}

class CinemaColumn(
    override val name : String = "cinema"
) : Column {
    override fun sort(list : List<DataRow>) : List<DataRow> = list.sortedBy { it.cinema }

    override fun search(list: List<DataRow>, input:String): List<DataRow> {
        return list.filter {
            it.cinema.contains(input)
        }
    }


}

class FilmColumn(
    override val name : String = "film"
) : Column {
    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.film }

    override fun search(list: List<DataRow>, input:String): List<DataRow> {
        return list.filter {
            it.film.contains(input)
        }
    }


}

class HallColumn(
    override val name : String = "hall"
) : Column{
    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.hall }

    override fun search(list: List<DataRow>, input:String): List<DataRow> {
        return list.filter {
            it.hall.toString().contains(input)
        }
    }


}

class DateColumn(
    override val name : String = "date"
) : Column {
    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.date }

    override fun search(list: List<DataRow>, input:String): List<DataRow> {
        return list.filter {
            it.date.contains(input)
        }
    }


}

class TimeColumn(
    override val name : String = "time"
) : Column {
    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.time }

    override fun search(list: List<DataRow>, input:String): List<DataRow> {
        return list.filter {
            it.time.contains(input)
        }
    }
}



class DataTable : Table {
    override var tab:MutableList<DataRow> =  mutableListOf()
    override fun getList() : List<DataRow> {
        return tab.toList()
    }
    override fun add(dataRow: DataRow) {
        this.tab.add(dataRow)
    }
    override fun set(index : Int, dataRow: DataRow) {
        this.tab[index] = dataRow
    }
    override fun delete(index : Int) {
        this.tab.removeAt(index)
    }
}
