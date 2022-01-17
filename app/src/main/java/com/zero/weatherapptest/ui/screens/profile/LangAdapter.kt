package com.zero.weatherapptest.ui.screens.profile

import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseAdapter
import com.zero.weatherapptest.base.ViewHolder
import com.zero.weatherapptest.data.objects.LangData
import com.zero.weatherapptest.databinding.ItemLanguageListBinding

class LangAdapter(private var listener:(land: LangData) -> Unit): BaseAdapter<LangData>(R.layout.item_language_list) {

    override fun bindViewHolder(holder: ViewHolder, data: LangData) {
        holder.itemView.apply {
            val binding = ItemLanguageListBinding.bind(this)
            data.apply {
                binding.tvLang.text = name
                binding.parent.setOnClickListener {
                    listener.invoke(this)
                }
            }
        }
    }

}