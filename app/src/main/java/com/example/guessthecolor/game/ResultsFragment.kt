package com.example.guessthecolor.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.guessthecolor.R
import com.example.guessthecolor.database.TrackRecords
import com.example.guessthecolor.database.TrackRecordsDatabase
import com.example.guessthecolor.database.TrackRecordsDatabaseDao
import com.example.guessthecolor.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentResultsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_results, container, false)
        binding.replayButton.setOnClickListener { view : View ->
            Navigation.findNavController(view).navigate(ResultsFragmentDirections.actionResultsFragmentToGameFragment())
        }
        binding.returnButton.setOnClickListener { view : View ->
            Navigation.findNavController(view).navigate(R.id.action_resultsFragment_to_titleScreenFragment)
        }
        var args = ResultsFragmentArgs.fromBundle(requireArguments())
        binding.resultsText.text = "Correct answers: ${args.totalCorrect.toString()}"

        val userDao = TrackRecordsDatabase.getInstance(requireNotNull(this.activity).application).trackRecordsDatabaseDao
        userDao.insert(TrackRecords(System.currentTimeMillis(), args.totalCorrect.toLong()))
        return binding.root

    }

}