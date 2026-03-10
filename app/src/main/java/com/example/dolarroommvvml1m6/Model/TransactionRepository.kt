package com.example.dolarroommvvml1m6.Model

import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionDao
import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionModel

class TransactionRepository(private val dao: DolarTransactionDao) {


    suspend fun insertTransaction(transactionModel: DolarTransactionModel) {

        dao.insertTransaction(transactionModel)
    }

    suspend fun getAllTransactions (): List<DolarTransactionModel>{

        return dao.getAllTransactions()
    }


}