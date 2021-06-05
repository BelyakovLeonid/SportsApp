package com.modo.modo.sportsapp.myevents.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modo.modo.sportsapp.databinding.EventItemBinding
import com.modo.modo.sportsapp.myevents.presentation.model.EventUiModel

class EventsAdapter(
    private val onItemClick: (EventUiModel) -> Unit
) : ListAdapter<EventUiModel, ItemViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

class ItemViewHolder(
    private val binding: EventItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventUiModel, onItemClick: (EventUiModel) -> Unit) = with(binding) {
        activeLabel.isVisible = item.isNearest
        name.text = item.name
        role.text = "ds exfcnbreb."
        timeText.text = item.date
        place.text = item.address
        container.setOnClickListener {
            onItemClick.invoke(item)
        }
        //todo
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<EventUiModel>() {
    override fun areItemsTheSame(oldItem: EventUiModel, newItem: EventUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventUiModel, newItem: EventUiModel): Boolean {
        return oldItem == newItem
    }
}