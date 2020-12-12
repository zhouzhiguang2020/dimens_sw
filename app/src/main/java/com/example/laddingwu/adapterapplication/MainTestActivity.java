package com.example.laddingwu.adapterapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laddingwu.adapterapplication.view.TickSeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainTestActivity extends AppCompatActivity {
    private TickSeekBar tick_seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_layout);
        tick_seekbar = findViewById(R.id.tick_seekbar);
        List<TickSeekBar.TickData> tickDataList = new ArrayList<>();
        TickSeekBar.TickData tickData1 = new TickSeekBar.TickData(10, android.R.color.white);
        tickDataList.add(tickData1);
        TickSeekBar.TickData tickData2 = new TickSeekBar.TickData(45, android.R.color
                .holo_orange_dark);
        tickDataList.add(tickData2);
        TickSeekBar.TickData tickData3 = new TickSeekBar.TickData(60, android.R.color.white);
        tickDataList.add(tickData3);
        TickSeekBar.TickData tickData4 = new TickSeekBar.TickData(90, android.R.color
                .holo_orange_dark);
        tickDataList.add(tickData4);
        tick_seekbar.setTicks(tickDataList);

    }
}
