package com.example.dolarroommvvml1m6.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dolarroommvvml1m6.Model.Local.DolarTransactionModel
import com.example.dolarroommvvml1m6.databinding.ItemTransactionBinding
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    private var transaction = listOf<DolarTransactionModel>()

    fun submitList(newList: List<DolarTransactionModel>) {
        transaction = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return TransactionHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.bind(transaction[position])
    }

    override fun getItemCount(): Int =transaction.size


    inner class TransactionHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(transaction: DolarTransactionModel) {

            binding.textViewType.text = "${transaction.type} - ${transaction.amount} USD"

            val formatDate = SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            )
                .format((Date(transaction.timestamp)))

            binding.textViewTimestamp.text = formatDate
        }
    }
}