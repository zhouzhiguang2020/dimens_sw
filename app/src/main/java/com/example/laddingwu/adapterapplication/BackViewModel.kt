package com.example.laddingwu.adapterapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.apkfuns.logutils.LogUtils

/**
 * Created by .
 * User: ASUS
 * Date: 2020/12/12
 * Time: 17:59
 */
class BackViewModel(application: Application) : AndroidViewModel(application) {

    fun Test() {
        LogUtils.e("你妹妹的")
    }
}