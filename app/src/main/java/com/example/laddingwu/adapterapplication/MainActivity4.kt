package com.example.laddingwu.adapterapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apkfuns.logutils.LogUtils
import com.example.laddingwu.adapterapplication.fragment.BlankFragment
import com.example.laddingwu.adapterapplication.fragment.TestViewModleFragment

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        var fragment1 = BlankFragment()
        var fragment2 = TestViewModleFragment()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment1)
        transaction.add(R.id.container, fragment2, "s")
        transaction.hide(fragment1)
        transaction.commit()
        var temp = supportFragmentManager.findFragmentByTag("s")
        LogUtils.w("看看为空吗：" + temp == null)
//        transaction.hide(temp!!)
//        transaction.commit()

    }

}