package cn.edu.sicnu.cs.stu.xiaoquan.myapplication_midexam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val dataList: List<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val tvRecord = itemView.findViewById<android.widget.TextView>(R.id.tvItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = dataList[position]
        holder.tvRecord.text = item.resultStr
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
