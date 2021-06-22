package com.example.bmicalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        //다음 화면으로 넘어가기
        resultButton.setOnClickListener {
            /*val intent = Intent(this,resultActivity::class.java)
            intent.putExtra("weight, weightEditText.text.toString())
            intent.putExtra("height, heightEditText.text.toString())
            startActivity(intent)*/                  //without anko

            //with anko
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())
            startActivity<resultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }
    private fun saveData(height: Int, weight:Int)   //데이터 저장하기, 불러오기를 위한 기능
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
        editor.putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData()   //데이터 저장하기, 불러오기를 위한 기능
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT",0)
        val weight = pref.getInt("KEY_WEIGHT",0)

        if(height != 0 && weight != 0)
        {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}