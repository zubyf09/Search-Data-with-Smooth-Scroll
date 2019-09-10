package codenevisha.ir.mvvmwithdagger.data.db

import androidx.room.*
import com.vivy.doctorfinder.data.model.Doctor

@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor: Doctor):Long

    @Delete
    fun deleteDoctor(doctor: Doctor):Int

    @Query("SELECT * FROM Doctor")
    fun getAllDoctor():MutableList<Doctor>
}