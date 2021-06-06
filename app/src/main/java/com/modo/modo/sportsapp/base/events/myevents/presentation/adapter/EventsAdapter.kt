package com.modo.modo.sportsapp.base.events.myevents.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.EmptyEventItemBinding
import com.modo.modo.sportsapp.databinding.EventItemBinding
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus

class EventsAdapter(
    private val onItemClick: (EventItem.EventUiModel) -> Unit
) : ListAdapter<EventItem, RecyclerView.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            EVENT_TYPE -> {
                val binding = EventItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
            else -> {
                val binding = EmptyEventItemBinding.inflate(layoutInflater, parent, false)
                return EmptyViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(getItem(position) as EventItem.EventUiModel, onItemClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is EventItem.EventUiModel -> EVENT_TYPE
            is EventItem.EmptyEventUiModel -> EMPTY_TYPE
        }
    }

    companion object {
        private const val EMPTY_TYPE = 1
        private const val EVENT_TYPE = 2
    }
}

class ItemViewHolder(
    private val binding: EventItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventItem.EventUiModel, onItemClick: (EventItem.EventUiModel) -> Unit) = with(binding) {
        activeLabel.isVisible = item.isOpen
        name.text = item.name
        timeText.text = item.date
        place.text = item.address
        container.setOnClickListener {
            onItemClick.invoke(item)
        }
        val strId = if (item.userStatus == ParticipantStatus.FAN) {
            R.string.detail_you_are_fun
        } else {
            R.string.detail_you_are_sport
        }
        role.setText(strId)
        role.isVisible = item.userStatus != ParticipantStatus.NONE
    }
}

class EmptyViewHolder(
    binding: EmptyEventItemBinding
) : RecyclerView.ViewHolder(binding.root)

class RecipeDiffCallback : DiffUtil.ItemCallback<EventItem>() {
    override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return when {
            oldItem is EventItem.EventUiModel && newItem is EventItem.EventUiModel -> newItem.id == oldItem.id
            else -> oldItem.javaClass == newItem.javaClass
        }
    }

    override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem == newItem
    }
}