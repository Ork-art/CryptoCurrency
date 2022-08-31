package com.ork.cryptocurrency.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ork.cryptocurrency.R
import com.ork.cryptocurrency.databinding.ItemRowBinding
import com.ork.cryptocurrency.model.CryptoModel

class RecyclerViewAdapter(private val cryptoList:ArrayList<CryptoModel>, private val listener:Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors :Array<String> = arrayOf("#4ddbdb", "#8de8e8", "#a4daef", "#aaddee", "#009489")


    class RowHolder(view:View):RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel, colors:Array<String>, position: Int, listener:Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 5]))
            itemView.findViewById<TextView>(R.id.text_name).text = cryptoModel.currency
            itemView.findViewById<TextView>(R.id.text_price).text = cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position], colors, position, listener)
    }

    override fun getItemCount(): Int {
       return cryptoList.size
    }
}