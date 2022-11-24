package com.example.laddingwu.adapterapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.FileUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.OnVideoSavedCallback
import androidx.camera.view.video.OutputFileOptions
import androidx.camera.view.video.OutputFileResults
import androidx.core.content.ContextCompat
import com.apkfuns.logutils.LogUtils
import com.dylanc.viewbinding.binding
import com.dylanc.viewbinding.nonreflection.binding
import com.example.laddingwu.adapterapplication.config.CommonConfig
import com.example.laddingwu.adapterapplication.databinding.ActivityShootVideoLayoutBinding
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

import java.io.File


// 测试录制视频
class ShootVideoActivity : AppCompatActivity(), View.OnClickListener {
    private var mCameraController: LifecycleCameraController? = null
    private val  binding:ActivityShootVideoLayoutBinding by binding<ActivityShootVideoLayoutBinding>()
    private var isRecording = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoot_video_layout)

        initCanmer()
        binding.shootVideo.setOnClickListener(this)
    }

    //初始化相机
    private fun initCanmer() {
        XXPermissions.with(this) // 申请安装包权限
            //.permission(Permission.REQUEST_INSTALL_PACKAGES)
            // 申请悬浮窗权限
            //.permission(Permission.SYSTEM_ALERT_WINDOW)
            // 申请通知栏权限
            //.permission(Permission.NOTIFICATION_SERVICE)
            // 申请系统设置权限
            //.permission(Permission.WRITE_SETTINGS)
            // 申请单个权限
            .permission(Permission.CAMERA)
            .permission(Permission.RECORD_AUDIO)
            .permission(Permission.Group.STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    if (all) {
                        initcan()
                    } else {
                        Toast.makeText(
                            this@ShootVideoActivity,
                            "获取部分权限成功，但部分权限未正常授予",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onDenied(permissions: List<String>, never: Boolean) {
                    if (never) {

                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(this@ShootVideoActivity, permissions)
                    } else {

                    }
                }
            })

    }

    private fun initcan() {
        mCameraController = LifecycleCameraController(this)
        mCameraController!!.bindToLifecycle(this)
        //默认后置摄像头
        mCameraController!!.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        // if (mConfig.isCameraAroundState) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
        binding.cameraPreviewView.setController(mCameraController)
        //设置闪光灯模式
        mCameraController!!.imageCaptureFlashMode = ImageCapture.FLASH_MODE_AUTO
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.shoot_video -> {
                if (!isRecording) {
                    //开始录制视频了
                    recordStart()
                    isRecording = true
                    LogUtils.w("开始录制了")
                } else {

                    isRecording=false
                    mCameraController!!.stopRecording()
                    LogUtils.w("停止录制了")
                }
            }
        }
    }

    //开始拍摄视频了
    @SuppressLint("UnsafeOptInUsageError")
    private fun recordStart() {
        var currenttime = getTimeStame()
        var dirfile = File(CommonConfig.getInstance().PATH_APP_VIDEO)
        if (!dirfile.exists()) {

            //确保图片文件夹存在
            org.apache.commons.io.FileUtils.forceMkdir(dirfile)
        }
        var videoFilePath =
            CommonConfig.getInstance().PATH_APP_VIDEO + File.separator + currenttime + ".mp4"
        LogUtils.e("看看视频的位置：" + videoFilePath)
        var videofile = File(videoFilePath)
        if (!videofile.exists()) {
            videofile.createNewFile()
        }
        mCameraController!!.setEnabledUseCases(LifecycleCameraController.VIDEO_CAPTURE)
        val fileOptions: OutputFileOptions = OutputFileOptions.builder(videofile).build()
        mCameraController!!.startRecording(
            fileOptions,
            ContextCompat.getMainExecutor(this),
            object :
                OnVideoSavedCallback {
                override fun onVideoSaved(outputFileResults: OutputFileResults) {
                    var outuri = outputFileResults.savedUri
                    LogUtils.w("打印当前录视频返回结果" + outuri)
                }

                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {

                }

            })

    }

    /**
     * 获取当前的时间戳
     *
     * @return
     */
    fun getTimeStame(): String? {
        //获取当前的毫秒值
        val time = System.currentTimeMillis()
        //将毫秒值转换为String类型数据
        //返回出去
        return time.toString()
    }

    fun unbindCameraController() {
        if (mCameraController != null) {
            mCameraController!!.unbind()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindCameraController()
    }
}