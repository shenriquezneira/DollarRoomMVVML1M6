package com.example.dolarroommvvml1m6.Model.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dolar_transaction")
data class DolarTransactionModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String, //"BUY" o "SELL"
    val amount: Double,
    val timestamp: Long = System.currentTimeMillis()
)