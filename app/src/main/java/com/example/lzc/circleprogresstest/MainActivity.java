package com.example.lzc.circleprogresstest;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static CircularProgressButton viewById;
    private static int progress = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                progress += 50;
                viewById.setProgress(progress);
            }
        }
    };
    private BarChart barChat;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private LineChart lineChart;
    private RadarChart radarChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TinkerInstaller.onReceiveUpgradePatch(this,cacheFilePath);
        Toast.makeText(this, "补丁生效了", Toast.LENGTH_SHORT).show();
        viewById = (CircularProgressButton) findViewById(R.id.btnWithText);
        viewById.setIndeterminateProgressMode(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 1000, 1000);
        barChat = (BarChart) findViewById(R.id.barchat);
        lineChart = (LineChart) findViewById(R.id.linechart);
        radarChat = (RadarChart) findViewById(R.id.radarchart);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);
        colours.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");
        names.add("折线四");
        BarChartManager barChartManager = new BarChartManager(barChat);
        barChartManager.showBarChart(xValues,yValues.get(0),names.get(1),colours.get(2));
        barChartManager.setXAxis(12f,0f,12);
        barChartManager.showBarChart(xValues,yValues,names,colours);

        //折线图
        LineChartManager lineChartManager = new LineChartManager(lineChart);
        lineChartManager.showLineChart(xValues,yValues.get(0),names.get(1),colours.get(0));
        lineChartManager.showLineChart(xValues,yValues,names,colours);
        //雷达图
        RadarChartManager radarChartManager = new RadarChartManager(radarChat);
        radarChartManager.showRadarChart(xValues,yValues.get(0),names.get(0),colours.get(0));
        radarChartManager.showRadarChart(xValues,yValues,names,colours);
    }

    static class MyTask extends java.util.TimerTask {
        public void run() {
            progress++;
        }
    }
}
