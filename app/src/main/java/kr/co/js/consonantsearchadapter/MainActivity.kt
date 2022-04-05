package kr.co.js.consonantsearchadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvList = findViewById<RecyclerView>(R.id.rv_list)
        val etSearch = findViewById<EditText>(R.id.et_search)

        val list = arrayOf(
            "사과",
            "딸기",
            "바나나",
            "수박",
            "체리",
            "망고",
            "메론",
            "당근",
            "레몬",
            "오이",
            "고추",
            "옥수수",
            "고구마",
            "감자",
            "감지",
            "솨과",
            "수과",
            "똘기",
            "슴박"
        )

        val adapter = ConsonantSearchAdapter(list)
        rvList.adapter = adapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Log.d(localClassName, "beforeTextChanged $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Log.d(localClassName, "onTextChanged $p0")
                adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
                //Log.d(localClassName, "afterTextChanged $p0")
            }

        })
    }
}