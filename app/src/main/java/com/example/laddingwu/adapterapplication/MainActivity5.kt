package com.example.laddingwu.adapterapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType


@ExperimentalUnitApi
class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material.Text(text = "你妹妹的")
            MessageCard(name = "你密码的")
        }
    }


    @Composable
    fun MessageCard(name: String) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top) {
            Text(text = "Hello $name!")
            Text(
                text = "未来教育： $name!", color = Color(0xFF3700B3), fontSize = TextUnit(
                    16.toFloat(), TextUnitType.Sp
                ), fontStyle = FontStyle(1), fontWeight = FontWeight.SemiBold


            )
        }

    }
}