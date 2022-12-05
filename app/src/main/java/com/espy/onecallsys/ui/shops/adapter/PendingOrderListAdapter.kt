package com.espy.onecallsys.ui.shops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.espy.onecallsys.R
import com.espy.onecallsys.api.models.shops.PendingOrder
import com.espy.onecallsys.utils.CommonUtils

class PendingOrderListAdapter internal constructor(private val context: Context, private val mData: List<PendingOrder>, val clickHandler: (order: PendingOrder) -> Unit) : RecyclerView.Adapter<PendingOrderListAdapter.ViewHolder>()  {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ordNo: TextView = itemView.findViewById(R.id.ordNo)
        internal var invNo: TextView = itemView.findViewById(R.id.invNo)
        internal var invDate: TextView = itemView.findViewById(R.id.invDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.row_item_order_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData[position]

        holder.ordNo.text = "Order No: " + data.id

        holder.invNo.text = "Invoice No: " + data.invoice_id

        holder.invDate.text = "Invoice Date: " + CommonUtils.getConvertedDate(data.date)

        holder.itemView.setOnClickListener {
            clickHandler(data)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}