package cn.edu.sicnu.cs.stu.xiaoquan.myapplication_midexam

import java.io.Serializable

data class HistoryItem(
    val expression: String,
    val resultStr: String
) : Serializable
