package cn.edu.sicnu.cs.stu.xiaoquan.myapplication_midexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val list = intent.getSerializableExtra("history_data") as? ArrayList<HistoryItem> ?: arrayListOf()
        val rv: RecyclerView = findViewById(R.id.rvHistory)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HistoryAdapter(list)
    }
}
