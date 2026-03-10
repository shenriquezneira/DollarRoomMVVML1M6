package com.example.dolarroommvvml1m6.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionDao
import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionModel

@Database(entities = [DolarTransactionModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dolarTransactionDao(): DolarTransactionDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            //si esxiste la instancia la retorna
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "dolar_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }


    }


}