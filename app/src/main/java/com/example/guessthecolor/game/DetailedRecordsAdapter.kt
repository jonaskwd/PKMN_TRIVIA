package com.example.guessthecolor.game

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.guessthecolor.R
import com.example.guessthecolor.database.TrackRecords
import com.example.guessthecolor.databinding.TextItemViewBinding
import java.text.DateFormat

class DetailedRecordsAdapter : androidx.recyclerview.widget.ListAdapter<TrackRecords, DetailedRecordsAdapter.ViewHolder>(DetailedRecordsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TextItemViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.gameScore.text = item.score.toString()
        holder.dateTime.text =  DateFormat.getInstance().format(item.dateTime)
    }

    class ViewHolder(val binding: TextItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        val gameScore: TextView = binding.gameScore
        val dateTime: TextView = binding.dateTime
    }
}

class DetailedRecordsDiffCallback : DiffUtil.ItemCallback<TrackRecords>() {
    override fun areItemsTheSame(oldItem: TrackRecords, newItem: TrackRecords): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: TrackRecords, newItem: TrackRecords): Boolean {
        return oldItem == newItem
    }
}
