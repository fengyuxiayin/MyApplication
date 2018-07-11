package com.example.lzc.circleprogresstest;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/6/10.
 */

public class RadarChartManager {
    private RadarChart mRadarChart;
//    private YAxis leftAxis;
//    private YAxis rightAxis;
    private XAxis xAxis;
    private YAxis yAxis;

    public RadarChartManager(RadarChart radarChat) {
        this.mRadarChart = radarChat;
//        leftAxis = mRadarChart.getAxisLeft();
//        rightAxis = mRadarChart.getAxisRight();
        yAxis = mRadarChart.getYAxis();
        xAxis = mRadarChart.getXAxis();
    }

    /**
     * 初始化RadarChart
     */
    private void initRadarChart() {
        //背景颜色
        mRadarChart.setBackgroundColor(Color.WHITE);
        //网格
//        mRadarChart.setDrawGridBackground(false);
        //背景阴影
//        mRadarChart.setDrawBarShadow(false);
//        mRadarChart.setHighlightFullBarEnabled(false);

        //显示边界
//        mRadarChart.setDrawBorders(true);
        //设置动画效果
        mRadarChart.animateY(1000, Easing.EasingOption.Linear);
        mRadarChart.animateX(1000, Easing.EasingOption.Linear);

        //左下角的图例 标签 设置
        Legend legend = mRadarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //设置图例间距
        legend.setXEntrySpace(2f);
        legend.setYEntrySpace(2f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //XY轴的设置
        // X坐标值字体样式
//         xAxis.setTypeface(tf);
        // X坐标值字体大小
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.RED);
        // Y坐标值字体样式
        // yAxis.setTypeface(tf);
        // Y坐标值标签个数
        yAxis.setLabelCount(10, false);
        // Y坐标值字体大小
        yAxis.setTextSize(15f);
        // Y坐标值是否从0开始
        yAxis.setStartAtZero(true);
        // 是否显示y值在图表上
         yAxis.setDrawLabels(false);
        //X轴设置显示位置在底部
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
//        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);
    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showRadarChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initRadarChart();
        ArrayList<RadarEntry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new RadarEntry(yAxisValues.get(i)));
        }
        // 每一个BarDataSet代表一类柱状图
        RadarDataSet radarDataSet = new RadarDataSet(entries, label);

        radarDataSet.setColor(color);
        radarDataSet.setValueTextSize(9f);
        radarDataSet.setFormLineWidth(1f);
        radarDataSet.setFormSize(15.f);

        ArrayList<IRadarDataSet> dataSets = new ArrayList<>();
        dataSets.add(radarDataSet);
        RadarData data = new RadarData(dataSets);
        //设置X轴的刻度数
        mRadarChart.setData(data);
    }

    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showRadarChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initRadarChart();
        RadarData data = new RadarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<RadarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                entries.add(new RadarEntry(yAxisValues.get(i).get(j)));
            }
            RadarDataSet radarDataSet = new RadarDataSet(entries, labels.get(i));

            radarDataSet.setColor(colours.get(i));
            radarDataSet.setValueTextColor(colours.get(i));
            radarDataSet.setValueTextSize(10f);
            radarDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(radarDataSet);
        }
        mRadarChart.setData(data);
    }


    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
//        leftAxis.setAxisMaximum(max);
//        leftAxis.setAxisMinimum(min);
//        leftAxis.setLabelCount(labelCount, false);
//
//        rightAxis.setAxisMaximum(max);
//        rightAxis.setAxisMinimum(min);
//        rightAxis.setLabelCount(labelCount, false);
        mRadarChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, true);

//        mRadarChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
//        leftAxis.addLimitLine(hightLimit);
        mRadarChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
//        leftAxis.addLimitLine(hightLimit);
        mRadarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mRadarChart.setDescription(description);
        mRadarChart.invalidate();
    }
    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str,float x, float y) {
        Description description = new Description();
        description.setText(str);
        description.setXOffset(x);
        description.setYOffset(y);
        mRadarChart.setDescription(description);
        mRadarChart.invalidate();
    }
}