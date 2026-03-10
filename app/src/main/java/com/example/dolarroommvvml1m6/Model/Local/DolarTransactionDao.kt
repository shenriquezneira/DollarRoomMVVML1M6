package com.example.dolarroommvvml1m6.Model.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionModel

@Dao
interface DolarTransactionDao {

    @Insert
    suspend fun insertTransaction(transactionModel: DolarTransactionModel)

    @Query("SELECT * FROM dolar_transaction ORDER BY timestamp DESC")
    suspend fun getAllTransactions(): List<DolarTransactionModel>
}