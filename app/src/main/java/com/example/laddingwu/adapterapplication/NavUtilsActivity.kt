package com.example.laddingwu.adapterapplication

import android.content.DialogInterface
import android.content.Intent
import android.hardware.biometrics.BiometricPrompt

import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils

import com.apkfuns.logutils.LogUtils
import kotlinx.android.synthetic.main.activity_nav_utils_layout.*

class NavUtilsActivity : AppCompatActivity() {
    private var TAG = "指纹识别"
    val viewModel by viewModels<BackViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_utils_layout)
        button1.setOnClickListener {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                var mBiometricPrompt = BiometricPrompt.Builder(this)
                        .setTitle("指纹验证")
                        .setDescription("描述")
                        .setNegativeButton("取消", mainExecutor, object : DialogInterface.OnClickListener {
                            override fun onClick(dialogInterface: DialogInterface?, i: Int) {
                                Log.i(TAG, "Cancel button clicked")
                            }
                        })
                        .build()

                var mCancellationSignal = CancellationSignal()
                mCancellationSignal.setOnCancelListener(object : CancellationSignal.OnCancelListener {
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

                mBiometricPrompt.authenticate(mCancellationSignal, mainExecutor, mAuthenticationCallback)

            }
        }
    }
}