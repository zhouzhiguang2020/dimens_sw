package com.example.laddingwu.adapterapplication.resultcontract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.laddingwu.adapterapplication.TestActivityResultActivity

/**
 * Created by .
 * User: ASUS
 * Date: 2021/8/3
 * Time: 16:57
 * 测试新的activity跳转返回值
 *
 *
StartActivityForResult: 通用的Contract,不做任何转换，Intent作为输入，ActivityResult作为输出，这也是最常用的一个协定。
RequestMultiplePermissions： 用于请求一组权限
RequestPermission: 用于请求单个权限
TakePicturePreview: 调用MediaStore.ACTION_IMAGE_CAPTURE拍照，返回值为Bitmap图片
TakePicture: 调用MediaStore.ACTION_IMAGE_CAPTURE拍照，并将图片保存到给定的Uri地址，返回true表示保存成功。
TakeVideo: 调用MediaStore.ACTION_VIDEO_CAPTURE 拍摄视频，保存到给定的Uri地址，返回一张缩略图。
PickContact: 从通讯录APP获取联系人
GetContent: 提示用选择一条内容，返回一个通过ContentResolver#openInputStream(Uri)访问原生数据的Uri地址（content://形式） 。默认情况下，它增加了 Intent#CATEGORY_OPENABLE, 返回可以表示流的内容。
CreateDocument: 提示用户选择一个文档，返回一个(file:/http:/content:)开头的Uri。
OpenMultipleDocuments: 提示用户选择文档（可以选择多个），分别返回它们的Uri，以List的形式。
OpenDocumentTree: 提示用户选择一个目录，并返回用户选择的作为一个Uri返回，应用程序可以完全管理返回目录中的文档。
 */
class TestActivityResultContract : ActivityResultContract<String, String>() {
    fun createIntents(context: Context, input: String?): Intent {
        var intent = Intent(context, TestActivityResultActivity::class.java)
        intent.putExtra("text", input)
        return intent

    }

    override fun createIntent(context: Context, input: String): Intent {
        return createIntents(context, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        val data = intent?.getStringExtra("result")
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else {
            return ""
        }

    }
}