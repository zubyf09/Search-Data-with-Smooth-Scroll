package codenevisha.ir.mvvmwithdagger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorfinder.data.model.SearchModel

@Database(entities = [Doctor::class] , version = AppDatabase.VERSION , exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    companion object {
        const val DB_NAME = "Vivy.db"
        const val VERSION = 1
    }

    abstract fun searchDao(): DoctorDao
}