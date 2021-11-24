package com.example.tryapp

import androidx.room.*

@Entity
data class LocalModel(
    val whole1 : String ,
    val denominator1 : String,
    val numerator1: String,
    val whole2 : String ,
    val denominator2 : String,
    val numerator2: String ,
    val wholeRes : String ,
    val denominatorRes : String,
    val numeratorRes: String

    ){
    @PrimaryKey(autoGenerate = true) var id : Int? = null
}

@Dao
interface LocalDao {

    @Insert
    fun insert(list : LocalModel)

    @Query("SELECT * FROM LocalModel")
    fun getAll() : LocalModel

    @Query("DELETE FROM LocalModel")
    fun deleteAll()

}

@Database(entities = [LocalModel::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun localDao() : LocalDao
}