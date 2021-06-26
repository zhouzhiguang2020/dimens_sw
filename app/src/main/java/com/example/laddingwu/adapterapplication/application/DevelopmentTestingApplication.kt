package com.example.laddingwu.adapterapplication.application

import android.app.Application
import android.content.Context
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import com.example.laddingwu.adapterapplication.config.CommonConfig

/**
 * Created by .
 * User: ASUS
 * Date: 2021/6/23
 * Time: 10:26
 */
class DevelopmentTestingApplication : Application(), CameraXConfig.Provider {
    companion object {
        //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
        var mContext //上下文
                : Context? = null

        open fun getContext(): Context? {
            return mContext
        }
        var mApplication: DevelopmentTestingApplication? = null
        open fun getInstance() = mApplication
    }
    override fun onCreate() {
        super.onCreate()
        CommonConfig.getInstance().initFileDir()
    }

    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }
}