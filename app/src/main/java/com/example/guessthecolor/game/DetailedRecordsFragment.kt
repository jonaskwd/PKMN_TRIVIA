package com.example.guessthecolor.game

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.guessthecolor.R
import com.example.guessthecolor.database.TrackRecordsDatabase
import com.example.guessthecolor.database.TrackRecordsDatabaseDao
import com.example.guessthecolor.databinding.FragmentDetailedRecordsBinding
import com.example.guessthecolor.databinding.FragmentResultsBinding


class DetailedRecordsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
            val binding: FragmentDetailedRecordsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detailed_records, container, false)

            val application = requireNotNull(this.activity).application
            val dataSource = TrackRecordsDatabase.getInstance(application).trackRecordsDatabaseDao
            val viewModelFactory = DetailedRecordsViewModelFactory(dataSource, application)

            val detailedRecordViewModel = ViewModelProvider(this, viewModelFactory).get(DetailedRecordViewModel::class.java)

            binding.lifecycleOwner = this

            val adapter = DetailedRecordsAdapter()
            binding.recordList.adapter = adapter

            detailedRecordViewModel.scores.observe(viewLifecycleOwner, Observer{
                it?.let{
                    adapter.submitList(it)
                }
            })

            binding.returnButton.setOnClickListener { view : View ->
                Navigation.findNavController(view).navigate(R.id.action_detailedRecordsFragment_to_trackRecordFragment)
            }

            return binding.root
    }
}