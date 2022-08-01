package com.jeromedusanter.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleBaseAdapter<B : ViewDataBinding, T : BaseItem>(
    private var listener: Listener? = null,
) : ListAdapter<T, RecyclerView.ViewHolder>(DiffCallback<T>()) {

    abstract val resId: Int

    abstract fun setVariable(item: T)

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context),
            resId,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        setVariable(item)
        holder.itemView.setOnClickListener { listener?.onClickItem(item.id) }
    }

    private class DiffCallback<T : BaseItem> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }

    class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClickItem(itemId: Int)
    }
}