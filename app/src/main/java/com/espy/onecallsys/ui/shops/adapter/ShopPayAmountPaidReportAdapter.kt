package com.espy.onecallsys.ui.shops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.espy.onecallsys.R
import com.espy.onecallsys.ui.shops.models.ShopPayHistory
import com.espy.onecallsys.ui.shops.models.ShopPayPaidAmountRepoData
import com.espy.onecallsys.utils.CommonUtils

class ShopPayAmountPaidReportAdapter  internal constructor(private val context: Context, private val mData: List<ShopPayPaidAmountRepoData?>, val clickHandler: (product: ShopPayHistory?) -> Unit) : RecyclerView.Adapter<ShopPayAmountPaidReportAdapter.ViewHolder>()  {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var rvDate: TextView = itemView.findViewById(R.id.rvDate)
        internal var tvAmnt: TextView = itemView.findViewById(R.id.tvAmnt)
        internal var tvPaymentType: TextView = itemView.findViewById(R.id.tvPaymentType)
        internal var tvCollectedBy: TextView = itemView.findViewById(R.id.tvCollectedBy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.row_item_shop_collection_paid_amount_report, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dat = mData[position]

        dat?.run{
            holder.rvDate.text = date
            date?.let {
                if (it.isNotEmpty() && it != "0"){
                    holder.rvDate.text = CommonUtils.getConvertedDate2(it)
                }else{
                    holder.rvDate.text = "--"
                }
            }?:run {
                holder.rvDate.text = "--"
            }

            holder.tvAmnt.text = context.getString(R.string.amount_rep_float, (amount?:"0.00").toFloat())

            when (payment_mode) {
                "1" -> {
                    holder.tvPaymentType.text = "Cash"
                }

                "2" -> {
                    holder.tvPaymentType.text = "Card"
                }
                "3" ->
                {
                    holder.tvPaymentType.text = "Gpay"
                }
            }
            added_by?.let {
                if (it != "0" && it.isNotEmpty()) {
                    holder.tvCollectedBy.text = it
                } else {
                    holder.tvCollectedBy.text = "--"
                }
            }?: run {
                holder.tvCollectedBy.text = "--"
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}