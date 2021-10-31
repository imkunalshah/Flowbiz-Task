package com.kunal.flowbiztask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kunal.flowbiztask.data.db.daos.QuestionsDao
import com.kunal.flowbiztask.data.network.models.Question

@Database(
    entities = [Question::class],
    version = 1
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getQuestionDao(): QuestionsDao

    companion object{

        @Volatile
        private var instance: AppDatabase? =  null

        private val LOCK = Any()

        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "App.db"
            ).build()
    }
}