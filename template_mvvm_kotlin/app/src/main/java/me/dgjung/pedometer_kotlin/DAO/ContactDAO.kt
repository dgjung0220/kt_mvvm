package me.dgjung.pedometer_kotlin.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import me.dgjung.pedometer_kotlin.DTO.Contact

@Dao
interface ContactDAO {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>
    
    // insert, update 등 갱신 함수에는 onConflict 속성 지정 가능
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact : Contact)

    @Delete
    fun delete(contact: Contact)
}