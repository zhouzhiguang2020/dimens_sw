package com.example.laddingwu.adapterapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.apkfuns.logutils.LogUtils



class TestActivityResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_result_layout)
        var msg = intent.getStringExtra("text")

        LogUtils.e("哥们接收到的数据是：" + msg)
        var text=findViewById<TextView>(R.id.text)
        text.setOnClickListener {
            var intent = Intent()
            intent.putExtra("result", "你妹妹的")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}