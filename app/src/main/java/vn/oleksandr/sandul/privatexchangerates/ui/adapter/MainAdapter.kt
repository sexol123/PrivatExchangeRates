package vn.oleksandr.sandul.privatexchangerates.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import vn.oleksandr.sandul.privatexchangerates.R
import vn.oleksandr.sandul.privatexchangerates.ui.CurrencyModel
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var data : List<CurrencyModel> = Collections.emptyList()

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item) {

        val textCcy : TextView = item.findViewById(R.id.text_ccy)
        val textBaseCcy : TextView = item.findViewById(R.id.text_base_ccy)
        val textBuy : TextView = item.findViewById(R.id.text_buy)
        val textSell : TextView = item.findViewById(R.id.text_sell)
    }

    fun setDate(list : List<CurrencyModel>?) {
        this.data = list!!
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view : View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_currency, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return data.size
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        holder.textBaseCcy.text = data[position].baseCcy
        holder.textCcy.text = data[position].ccy
        holder.textBuy.text = data[position].buy
        holder.textSell.text = data[position].sale
    }
}