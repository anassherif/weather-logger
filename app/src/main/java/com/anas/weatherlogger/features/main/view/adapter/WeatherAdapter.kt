package com.anas.weatherlogger.features.main.view.adapter

import com.anas.weatherlogger.R
import com.anas.weatherlogger.base.view.adapter.BaseRecyclerViewAdapter
import com.anas.weatherlogger.base.view.adapter.BaseViewHolder
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.features.main.view.listeners.OnClickListener
import com.anas.weatherlogger.utils.TimeUtils
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter(
    private val weatherList: MutableList<WeatherEntity>,
    private val onClickListener: OnClickListener<WeatherEntity>
) :
    BaseRecyclerViewAdapter(R.layout.item_weather) {
    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.itemView) {
            val weatherEntity = weatherList[position]
            tvTemp.text = String.format(
                context.getString(R.string.format_temp),
                weatherEntity.main?.temp.toString()
            )
            weatherEntity.date?.let { date ->
                tvDate.text =
                    String.format(
                        context.getString(R.string.format_date),
                        TimeUtils().getFormattedTime(date)
                    )
            }
            setOnClickListener {
                onClickListener.onClick(weatherEntity)
            }

        }
    }

    fun updateAdapter(list: List<WeatherEntity>) {
        weatherList.clear()
        weatherList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        weatherList.clear()
        notifyDataSetChanged()
    }
}