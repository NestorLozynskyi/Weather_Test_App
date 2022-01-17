package com.zero.weatherapptest.ui.screens.generals.generalsAdapters

import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseAdapter
import com.zero.weatherapptest.base.ViewHolder
import com.zero.weatherapptest.data.objects.CityData
import com.zero.weatherapptest.databinding.ItemCityListBinding

class CityAdapter(private val listener: (city: CityData) -> Unit): BaseAdapter<CityData>(R.layout.item_city_list) {

    override fun bindViewHolder(holder: ViewHolder, data: CityData) {
        holder.itemView.apply {
            val binding = ItemCityListBinding.bind(this)
            data.apply {
                with(binding){
                    when {
                        !components.city.isNullOrBlank() -> {
                            tvCityName.text = resources.getString(R.string.city, components.city)
                        }
                        !components.village.isNullOrBlank() -> {
                            tvCityName.text = resources.getString(R.string.village, components.village)
                        }
                        else -> tvCityName.text = resources.getString(R.string.not_a_settlement)
                    }
                    tvCountryCode.text = components.countryCode
                    tvRegion.text = components.state
                    parent.setOnClickListener {
                        listener.invoke(this@apply)
                    }
                }
            }
        }
    }
}