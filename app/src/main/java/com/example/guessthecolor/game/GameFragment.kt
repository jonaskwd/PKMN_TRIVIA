package com.example.guessthecolor.game

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.guessthecolor.game.GameFragmentDirections
import com.example.guessthecolor.R
import com.example.guessthecolor.database.TrackRecordsDatabase
import com.example.guessthecolor.database.TrackRecordsDatabaseDao
import com.example.guessthecolor.databinding.FragmentGameBinding
import com.example.guessthecolor.databinding.FragmentTrackRecordBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        Log.i("GameViewModel", "GVM CALLED!")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        var resourceId = resources.getIdentifier(viewModel.currentQuestion.value?.img, "drawable", context?.getPackageName())
        binding.colorImage.setImageResource(resourceId)

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { newCurrentQuestion ->
            resourceId = resources.getIdentifier(newCurrentQuestion.img, "drawable", context?.getPackageName())
            binding.colorImage.setImageResource(resourceId)
            binding.questionText.text = newCurrentQuestion.text})
        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newCurrentTime ->
            binding.countdown.text = newCurrentTime.toString()
        })


        binding.submitButton.setOnClickListener { view : View ->
            val userAnswer = binding.userInput.editableText.toString()
            if (viewModel.currentQuestion.value!!.answer.contains<String>(userAnswer.lowercase().trim())) {
                viewModel.nextQuestion()
                resourceId = resources.getIdentifier(viewModel.currentQuestion.value!!.img, "drawable", context?.getPackageName())
                binding.invalidateAll()
                binding.userInput.editableText.clear()
                viewModel.totalCorrect = viewModel.totalCorrect.plus(1)!!
            } else {
                Toast.makeText(getActivity(), "WRONG ANSWER", Toast.LENGTH_LONG).show()
                binding.userInput.editableText.clear()
            }
        }

        // EVENT GAME FINISHED
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                viewModel.totalCorrect = viewModel.totalCorrect.plus(1)!!
                Navigation.findNavController(requireView()).navigate(
                    GameFragmentDirections.actionGameFragmentToResultsFragment(
                        viewModel.totalCorrect ?: 0
                    )
                )
                viewModel.currentTime.value = 0L
            }
        })

        viewModel.eventGameFinishTime.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                Navigation.findNavController(requireView()).navigate(
                    GameFragmentDirections.actionGameFragmentToResultsFragment(
                        viewModel.totalCorrect ?: 0
                    )
                )
                viewModel.currentTime.value = 0L
            }
        })

        binding.game = this

        return binding.root
    }


}

