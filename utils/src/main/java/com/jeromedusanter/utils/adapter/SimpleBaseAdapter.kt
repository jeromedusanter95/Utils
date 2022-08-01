package com.jeromedusanter.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeromedusanter.utils.BR

abstract class SimpleBaseAdapter<B : ViewDataBinding, T : BaseItem>(
    private var listener: Listener? = null,
) : ListAdapter<T, SimpleBaseAdapter.ViewHolder>(DiffCallback<T>()) {

    abstract val resId: Int

    /**
     * Should be BR.item from your app
     */
    abstract val variableId: Int

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context),
            resId,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.binding.setVariable(variableId, item)
        holder.itemView.setOnClickListener { listener?.onClickItem(item.id) }
    }

    private class DiffCallback<T : BaseItem> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClickItem(itemId: Int)
    }
}