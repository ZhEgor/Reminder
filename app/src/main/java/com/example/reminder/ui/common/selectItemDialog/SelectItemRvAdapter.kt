package com.example.reminder.ui.common.selectItemDialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.databinding.ListitemItemBinding

class SelectItemRvAdapter(private val onItemClicked: (String) -> Unit) :
    ListAdapter<String, SelectItemRvAdapter.ItemHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ListitemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(currentList[position], onItemClicked)
    }

    class ItemHolder(private val binding: ListitemItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, onItemClicked: (String) -> Unit) {
            binding.apply {
                tvName.text = item
                itemView.setOnClickListener {
                    onItemClicked(item)
                }
            }
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
