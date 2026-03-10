package com.example.dolarroommvvml1m6.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionModel
import com.example.dolarroommvvml1m6.Model.TransactionRepository
import kotlinx.coroutines.launch


class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    private val _transactions = MutableLiveData<List<DolarTransactionModel>>()

    val transaction: LiveData<List<DolarTransactionModel>> = _transactions


    fun addTransaction(type: String, amount: Double) {

        val transaction = DolarTransactionModel(type = type, amount = amount)

        viewModelScope.launch {

            repository.insertTransaction(transaction)
            loadTransactions()
        }
    }

    fun loadTransactions() {

        viewModelScope.launch {

            val list = repository.getAllTransactions()
            _transactions.postValue(list)
        }
    }


}

//clase de factory para crear viewmodel en capas superiores
class TransactionViewModelFactory(private val repository: TransactionRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.example.dolarroommvvml1m6.ViewModel.TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            return TransactionViewModel(repository) as T

        }

        throw IllegalArgumentException("UnKnow viewModel class")

    }

}