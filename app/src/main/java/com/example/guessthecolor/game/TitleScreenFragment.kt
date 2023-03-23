package com.example.guessthecolor.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.guessthecolor.R
import com.example.guessthecolor.databinding.FragmentTitleScreenBinding

class TitleScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleScreenBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title_screen, container, false)
        binding.playButton.setOnClickListener { view : View ->
            Navigation.findNavController(view).navigate(R.id.action_titleScreenFragment_to_gameFragment)
        }
        binding.trackRecordButton.setOnClickListener { view : View ->
            Navigation.findNavController(view).navigate(R.id.action_titleScreenFragment_to_trackRecordFragment)
        }
        return binding.root
    }

}

