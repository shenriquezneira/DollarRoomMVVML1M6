package com.example.dolarroommvvml1m6.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolarroommvvml1m6.Model.Local.AppDataBase
import com.example.dolarroommvvml1m6.Model.TransactionRepository
import com.example.dolarroommvvml1m6.R
import com.example.dolarroommvvml1m6.ViewModel.TransactionViewModel
import com.example.dolarroommvvml1m6.ViewModel.TransactionViewModelFactory
import com.example.dolarroommvvml1m6.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = TransactionAdapter()

    private lateinit var repository: TransactionRepository

    private val viewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // hacer las conexiones para insertar datos

        repository = TransactionRepository(AppDataBase.getDataBase(requireContext()).dolarTransactionDao())
        // mostrar en el recycler view

        binding.recyclerViewTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTransactions.adapter = adapter


        // observamos los cambios en liveData  desde transacciones

        viewModel.transaction.observe(viewLifecycleOwner){


                list ->

            adapter.submitList(list)
        }
        viewModel.loadTransactions()


        // btn comprar

        binding.buttonBuy.setOnClickListener { handleTransacction("BUY") }
        // btn vender

        binding.buttonSell.setOnClickListener { handleTransacction("SELL") }



    }

    private fun  handleTransacction( type : String){

        val amountText = binding.editTextTransaction.text.toString()

        if(amountText.isEmpty()){

            Toast.makeText(requireContext(), "Ingrese un monto", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDouble()
        // guardar en la bd
        viewModel.addTransaction(type, amount)//agrega al repositorio y actualiza LiveData
        binding.editTextTransaction.text.clear()
        Toast.makeText(requireContext(), "$type $amount USD registrado", Toast.LENGTH_SHORT).show()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}