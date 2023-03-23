package com.example.guessthecolor.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.guessthecolor.R
import com.example.guessthecolor.database.TrackRecordsDatabase
import com.example.guessthecolor.database.TrackRecordsDatabaseDao
import com.example.guessthecolor.databinding.FragmentTrackRecordBinding
import com.squareup.picasso.Picasso

class TrackRecordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTrackRecordBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_track_record, container, false)
        binding.returnButton.setOnClickListener { view : View ->
            Navigation.findNavController(view).navigate(R.id.action_trackRecordFragment_to_titleScreenFragment)
        }
        binding.detailButton.setOnClickListener { view : View ->
            Navigation.findNavController(view).navigate(R.id.action_trackRecordFragment_to_detailedRecordsFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = TrackRecordsDatabase.getInstance(application).trackRecordsDatabaseDao
        val viewModelFactory = TrackRecordViewModelFactory(dataSource, application)

        val trackRecordViewModel = ViewModelProvider(this, viewModelFactory).get(TrackRecordViewModel::class.java)

        binding.lifecycleOwner = this

        binding.resetRecordsButton.setOnClickListener { view : View ->
            trackRecordViewModel.database.deleteData()
            trackRecordViewModel.getRecordsCoroutine()
        }

        trackRecordViewModel.bestScore.observe(viewLifecycleOwner, Observer { newBestScore ->
            binding.bestScore.text = newBestScore.toString()
        })
        trackRecordViewModel.lastScore.observe(viewLifecycleOwner, Observer { newLastScore ->
            binding.lastScore.text = newLastScore.toString()
        })

        Picasso.get().load("https://archives.bulbagarden.net/media/upload/e/e2/0906Sprigatito.png").into(binding.resultsImage);
        return binding.root
    }

}
