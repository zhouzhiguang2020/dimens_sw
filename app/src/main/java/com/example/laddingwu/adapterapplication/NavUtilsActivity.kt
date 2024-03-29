package com.example.laddingwu.adapterapplication

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.hardware.biometrics.BiometricPrompt
import android.net.Uri

import android.os.Bundle
import android.os.CancellationSignal
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.app.NavUtils

import com.apkfuns.logutils.LogUtils
import com.dylanc.viewbinding.binding
import com.dylanc.viewbinding.nonreflection.binding
import com.example.laddingwu.adapterapplication.databinding.ActivityNavUtilsLayoutBinding
import com.example.laddingwu.adapterapplication.resultcontract.TestActivityResultContract


class NavUtilsActivity : AppCompatActivity() {
    private var TAG = "指纹识别"
    private val viewModel: BackViewModel by viewModels()
    private val binding: ActivityNavUtilsLayoutBinding by binding<ActivityNavUtilsLayoutBinding>()

    private val myActivityLauncher =
        registerForActivityResult(TestActivityResultContract()) { result ->

            binding.returnMsg.text = "回传数据：$result"
        }
    private val myActivityLauncher1 =
        registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
            LogUtils.e("选择了一个目录后：" + it.toString())
        }

    @OptIn(ExperimentalUnitApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_utils_layout)
        binding.button1.setOnClickListener {
            viewModel.Test()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                var mBiometricPrompt = BiometricPrompt.Builder(this)
                    .setTitle("指纹验证")
                    .setDescription("描述")
                    .setNegativeButton(
                        "取消",
                        mainExecutor,
                        object : DialogInterface.OnClickListener {
                            override fun onClick(dialogInterface: DialogInterface?, i: Int) {
                                Log.i(TAG, "Cancel button clicked")
                            }
                        })
                    .build()

                var mCancellationSignal = CancellationSignal()
                mCancellationSignal.setOnCancelListener(object :
                    CancellationSignal.OnCancelListener {
                    override fun onCancel() {
                        LogUtils.e("用户已经取消了")
                    }

                })

                var mAuthenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Log.i(TAG, "onAuthenticationError $errString")
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        Log.i(TAG, "onAuthenticationSucceeded $result")
                        LogUtils.e("指纹验证成功")
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Log.i(TAG, "onAuthenticationFailed ")
                    }
                }

                mBiometricPrompt.authenticate(
                    mCancellationSignal,
                    mainExecutor,
                    mAuthenticationCallback
                )

            }
        }
        binding.button2.setOnClickListener {
            var intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(this@NavUtilsActivity, ShootVideoActivity::class.java)
                startActivity(intent)
            }

        })
        binding.button4.setOnClickListener {
            // myActivityLauncher.launch("我要传入数据有点大你忍一忍")
            var intent = Intent(this@NavUtilsActivity, MainActivity4::class.java)
            startActivity(intent)
//            var path= com.example.laddingwu.adapterapplication.utils.FileUtils.getAppRootPath(this)
//            val uri: Uri = Uri.parse(path.absolutePath)
//            myActivityLauncher1.launch(uri)
//            LogUtils.e("传入path是："+uri.toString())
        }
        binding.button5.setOnClickListener {
//            var intent = Intent(this@NavUtilsActivity, MainActivity5::class.java)
//            startActivity(intent)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            val startActivity =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    //此处进行数据接收（接收回调）
                    if (it.resultCode == Activity.RESULT_OK) {
                        //成功数据
                    }
                }
            //跳转方式
            startActivity.launch(intent)
        }
    }
}