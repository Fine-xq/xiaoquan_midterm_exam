package cn.edu.sicnu.cs.stu.xiaoquan.myapplication_midexam

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var rgOp: RadioGroup
    private lateinit var btnCalc: Button
    private lateinit var tvResult: TextView

    private val calculator = Calculator()
    private val historyList = arrayListOf<HistoryItem>()
    private var saveResultText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        rgOp = findViewById(R.id.rgOp)
        btnCalc = findViewById(R.id.btnCalc)
        tvResult = findViewById(R.id.tvResult)

        savedInstanceState?.let {
            saveResultText = it.getString("result_text", "")
            tvResult.text = saveResultText
            val oldList = it.getSerializable("history") as? ArrayList<HistoryItem>
            oldList?.let { list ->
                historyList.addAll(list)
            }
        }

        btnCalc.setOnClickListener {
            runCalculate()
        }
    }

    private fun runCalculate() {
        val text1 = etNum1.text.toString()
        val text2 = etNum2.text.toString()

        if (text1.isBlank() || text2.isBlank()) {
            Toast.makeText(this, "请填入两个数字", Toast.LENGTH_SHORT).show()
            return
        }

        val numA = text1.toDouble()
        val numB = text2.toDouble()
        val opId = rgOp.checkedRadioButtonId

        if (opId == -1) {
            Toast.makeText(this, "请选择运算符", Toast.LENGTH_SHORT).show()
            return
        }

        var opSymbol = ""
        var answer = 0.0
        try {
            when (opId) {
                R.id.rbAdd -> {
                    opSymbol = "+"
                    answer = calculator.add(numA, numB)
                }
                R.id.rbSub -> {
                    opSymbol = "-"
                    answer = calculator.sub(numA, numB)
                }
                R.id.rbMul -> {
                    opSymbol = "*"
                    answer = calculator.mul(numA, numB)
                }
                R.id.rbDiv -> {
                    opSymbol = "/"
                    answer = calculator.div(numA, numB)
                }
            }
            val expressionStr = "$numA $opSymbol $numB"
            val resultShow = "$expressionStr = $answer"
            tvResult.text = resultShow
            saveResultText = resultShow
            historyList.add(HistoryItem(expressionStr, resultShow))
        } catch (e: ArithmeticException) {
            val errMsg = "错误：除数不能为0"
            tvResult.text = errMsg
            saveResultText = errMsg
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result_text", saveResultText)
        outState.putSerializable("history", historyList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuHistory) {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("history_data", historyList)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
