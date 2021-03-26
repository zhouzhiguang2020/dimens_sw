package com.example.laddingwu.adapterapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.commit
import com.example.laddingwu.adapterapplication.fragment.TestViewModleFragment

class TestActivity : AppCompatActivity() {
    //val s: SocketAsyncTimeout=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
        supportFragmentManager.commit {
            replace(R.id.content, TestViewModleFragment())
            addToBackStack(null)
            //commitAllowingStateLoss()

        }
    }
}