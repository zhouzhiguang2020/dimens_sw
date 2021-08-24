package com.example.laddingwu.adapterapplication.view

import androidx.lifecycle.*
import com.apkfuns.logutils.LogUtils
import com.yanzhenjie.kalle.Kalle
import com.yanzhenjie.kalle.simple.SimpleCallback
import com.yanzhenjie.kalle.simple.SimpleResponse
import com.yanzhenjie.kalle.simple.cache.CacheMode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Created by .
 * User: ASUS
 * Date: 2020/12/17
 * Time: 8:59
 */
class MyViewModel : ViewModel() {
    fun testfun() {
        val user: LiveData<String> = liveData {
            Kalle.get("http://www.example.com/user")
                    .param("uid", "123")
                    .perform(object : SimpleCallback<String>() {


                        override fun onResponse(response: SimpleResponse<String, String>?) {

                        }

                    })
            this@liveData.emit("ddddd")
        }


        LogUtils.w("这是一个测试的数据")
    }



}