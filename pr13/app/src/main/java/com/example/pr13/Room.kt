package com.example.pr13

import androidx.room.*

@Entity
data class LocalModel(
    @ColumnInfo(name = "text") val text : String,
    @ColumnInfo(name = "type") val type : String
) {
    @PrimaryKey(autoGenerate = true) var id : Int? = null
}

@Dao
interface LocalDao {

    @Insert
    fun insert(list : LocalModel)

    @Query("SELECT * FROM LocalModel")
    fun getAll() : List<LocalModel>

    @Query("DELETE FROM LocalModel")
    fun deleteAll()

}

@Database(entities = [LocalModel::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun localDao() : LocalDao
}