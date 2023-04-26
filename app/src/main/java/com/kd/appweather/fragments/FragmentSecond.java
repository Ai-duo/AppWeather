package com.kd.appweather.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.kd.appweather.R;
import com.kd.appweather.beans.ShiDu;
import com.kd.appweather.beans.WenDu;
import com.kd.appweather.databinding.ActivitySecondBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.components.Legend.LegendPosition.ABOVE_CHART_LEFT;

public class FragmentSecond extends Fragment {
    ActivitySecondBinding secondBinding;
    BarChart wdchart,sdchart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (secondBinding == null) {
            secondBinding = DataBindingUtil.inflate(inflater, R.layout.activity_second, container, false);
            wdchart = secondBinding.wdchart;
            sdchart = secondBinding.sdchart;
            initWd(wdchart);
            initSd(sdchart);
        }

        if(wd!=null){
            secondBinding.setWd(wd);
        }
        if(sd!=null){
            secondBinding.setSd(sd);
        }
        if (wds != null) {
            upteWds(wds);
        }
        if (sds != null) {
           upteSds(sds);
        }
        return secondBinding.getRoot();
    }

    List<WenDu> wds;
    List<ShiDu> sds;
    DecimalFormat decimalFormat = new DecimalFormat("0.0");
    public void upteWds(List<WenDu> wds) {
        this.wds = wds;
        if(wdchart==null){
            return;
        }
        final ArrayList<BarEntry> values = new ArrayList<>();
        final ArrayList<String> xLabers = new ArrayList<>();
        for(int i=0;i<wds.size();i++){
            values.add(wds.get(i).getEntry(i));
            xLabers.add(wds.get(i).getHour());
        }

        BarDataSet lineDataSet = new BarDataSet(values,"温度曲线图");
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawValues(true); // 是否在点上绘制Value
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(25f);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return decimalFormat.format(value);
            }
        });
        wdchart.setExtraBottomOffset(10);
        wdchart.setExtraRightOffset(10);
        wdchart.setExtraLeftOffset(10);
        BarData lineData = new BarData(lineDataSet);
        lineData.setBarWidth(0.3f);
        wdchart.setEnabled(true);
        wdchart.animateX(4000);
        wdchart.setDescription(null);
        wdchart.setData(lineData);
        wdchart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String hour = "";
                try {
                    if (xLabers.size() > value) {
                        hour = xLabers.get((int) Math.ceil(value)) + "时";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return hour;
            }
        });
    }
    public void upteSds(List<ShiDu> sds) {
        this.sds = sds;
        if(sdchart==null){
            return;
        }
        final ArrayList<BarEntry> values = new ArrayList<>();
        final ArrayList<String> xLabers = new ArrayList<>();
        for(int i=0;i<sds.size();i++){
            values.add(sds.get(i).getEntry(i));
            xLabers.add(sds.get(i).getHour());
        }

        BarDataSet lineDataSet = new BarDataSet(values,"湿度曲线图");
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawValues(true); // 是否在点上绘制Value
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(25f);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return decimalFormat.format(value);
            }
        });
        sdchart.setExtraBottomOffset(10);
        sdchart.setExtraRightOffset(10);
        sdchart.setExtraLeftOffset(10);
        BarData lineData = new BarData(lineDataSet);
        lineData.setBarWidth(0.3f);
        sdchart.setEnabled(true);
        sdchart.animateX(4000);
        sdchart.setDescription(null);
        sdchart.setData(lineData);

        sdchart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String hour = "";
                try {
                    if (xLabers.size() > value) {
                        hour = xLabers.get((int) Math.ceil(value)) + "时";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                return hour;
            }
        });
    }
    String wd ,sd;
    public void updateWd(String wd){
        this.wd = wd;
        if(secondBinding!=null){
            secondBinding.setWd(wd);
        }
    }
    public void updateSd(String sd){
        this.sd = sd;
        if(secondBinding!=null){
            secondBinding.setSd(sd);
        }
    }

    private void initWd(BarChart chart) {
        chart.setNoDataText("暂无数据");
        chart.setDrawValueAboveBar(true);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        final Description description = new Description();//描述信息
        description.setEnabled(false);//是否可用
        chart.setDescription(description);//不然会显示默认的 Description。
        chart.setTouchEnabled(true); // 设置是否可以触摸
        chart.setDragEnabled(true);// 是否可以拖拽
        chart.setScaleEnabled(false);// 是否可以缩放
        chart.setPinchZoom(false);//y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
        chart.setDoubleTapToZoomEnabled(false);//是否允许双击进行缩放
        chart.setScaleXEnabled(false);//是否允许以X轴缩放
        chart.setDrawGridBackground(false);// 是否显示表格颜色
        chart.setGridBackgroundColor(Color.WHITE);// 表格的的颜色
        //chart.animateY(1000, Easing.Linear);//设置动画
        //chart.setExtraBottomOffset(-5f);//防止底部数据显示不完整，设置底部偏移量
//x轴配置
        XAxis xAxis = null;
        YAxis lyAxis = null;
        xAxis = chart.getXAxis();
        xAxis.setEnabled(true);//是否可用
        xAxis.setDrawLabels(true);//是否显示数值
        xAxis.setDrawAxisLine(true);//是否显示坐标线
        xAxis.setAxisLineColor(Color.WHITE);//设置坐标轴线的颜色
        xAxis.setAxisLineWidth(0.8f);//设置坐标轴线的宽度
        xAxis.setDrawGridLines(false);//是否显示竖直风格线
        xAxis.setTextColor(Color.WHITE);//X轴文字颜色
        xAxis.setGridColor(Color.WHITE);//网格线颜色
        xAxis.setTextSize(30f);//X轴文字大小

        // Log.i("TAG",values.size()+"");
        xAxis.setLabelCount(12, false);
        //xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴文字显示位置

        xAxis.setSpaceMin(0.5f);//左空白区大小
        xAxis.setSpaceMax(0.8f);//右空白区大小
//左y轴配置
        lyAxis = chart.getAxisLeft();
        lyAxis.setLabelCount(5, false);
        lyAxis.setSpaceMin(0.5f);//左空白区大小
        lyAxis.setSpaceMax(0.5f);//右空白区大小
        lyAxis.setEnabled(true);//是否可用
        lyAxis.setDrawLabels(true);//是否显示数值
        lyAxis.setDrawAxisLine(true);//是否显示坐标线
        lyAxis.setDrawGridLines(false);//是否显示水平网格线
        /*lyAxis.setDrawZeroLine(true);////是否绘制零线
        lyAxis.setZeroLineColor(Color.WHITE);
        lyAxis.setZeroLineWidth(0.8f);*/
        //lyAxis.enableGridDashedLine(10f, 8f, 0f);//网格虚线
        lyAxis.setGridColor(Color.WHITE);//网格线颜色
        lyAxis.setGridLineWidth(0.8f);//网格线宽度
        lyAxis.setAxisLineColor(Color.WHITE);//坐标线颜色
        lyAxis.setTextColor(Color.WHITE);//左侧文字颜色
        lyAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                //Log.i("TAG","value:"+value+hoursData.unit);
                return decimalFormat.format(value) +"℃";
            }
        });
        lyAxis.setTextSize(25f);//左侧文字大小
//右y轴配置
        YAxis ryAxis = chart.getAxisRight();
        ryAxis.setEnabled(false);//是否可用
//标签配置
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormSize(20f);
        legend.setTextSize(35f);
        legend.setFormLineWidth(5f);
        legend.setTextColor(Color.WHITE);
        legend.setPosition(ABOVE_CHART_LEFT);
        legend.setEnabled(true);//是否可用
    }
    private void initSd(BarChart chart) {
        chart.setNoDataText("暂无数据");
        chart.setDrawValueAboveBar(true);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        final Description description = new Description();//描述信息
        description.setEnabled(false);//是否可用
        chart.setDescription(description);//不然会显示默认的 Description。
        chart.setTouchEnabled(true); // 设置是否可以触摸
        chart.setDragEnabled(true);// 是否可以拖拽
        chart.setScaleEnabled(false);// 是否可以缩放
        chart.setPinchZoom(false);//y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
        chart.setDoubleTapToZoomEnabled(false);//是否允许双击进行缩放
        chart.setScaleXEnabled(false);//是否允许以X轴缩放
        chart.setDrawGridBackground(false);// 是否显示表格颜色
        chart.setGridBackgroundColor(Color.WHITE);// 表格的的颜色
        //chart.animateY(1000, Easing.Linear);//设置动画
        //chart.setExtraBottomOffset(-5f);//防止底部数据显示不完整，设置底部偏移量
//x轴配置
        XAxis xAxis = null;
        YAxis lyAxis = null;
        xAxis = chart.getXAxis();
        xAxis.setEnabled(true);//是否可用
        xAxis.setDrawLabels(true);//是否显示数值
        xAxis.setDrawAxisLine(true);//是否显示坐标线
        xAxis.setAxisLineColor(Color.WHITE);//设置坐标轴线的颜色
        xAxis.setAxisLineWidth(0.8f);//设置坐标轴线的宽度
        xAxis.setDrawGridLines(false);//是否显示竖直风格线
        xAxis.setTextColor(Color.WHITE);//X轴文字颜色
        xAxis.setGridColor(Color.WHITE);//网格线颜色
        xAxis.setTextSize(30f);//X轴文字大小


        // Log.i("TAG",values.size()+"");
        xAxis.setLabelCount(12, false);
        //xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴文字显示位置

        xAxis.setSpaceMin(0.5f);//左空白区大小
        xAxis.setSpaceMax(0.8f);//右空白区大小
//左y轴配置
        lyAxis = chart.getAxisLeft();
        lyAxis.setLabelCount(5, false);
        lyAxis.setSpaceMin(0.5f);//左空白区大小
        lyAxis.setSpaceMax(0.5f);//右空白区大小
        lyAxis.setEnabled(true);//是否可用
        lyAxis.setDrawLabels(true);//是否显示数值
        lyAxis.setDrawAxisLine(true);//是否显示坐标线
        lyAxis.setDrawGridLines(false);//是否显示水平网格线
        /*lyAxis.setDrawZeroLine(true);////是否绘制零线
        lyAxis.setZeroLineColor(Color.WHITE);
        lyAxis.setZeroLineWidth(0.8f);*/
        //lyAxis.enableGridDashedLine(10f, 8f, 0f);//网格虚线
        lyAxis.setGridColor(Color.WHITE);//网格线颜色
        lyAxis.setGridLineWidth(0.8f);//网格线宽度
        lyAxis.setAxisLineColor(Color.WHITE);//坐标线颜色
        lyAxis.setTextColor(Color.WHITE);//左侧文字颜色
        lyAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                //Log.i("TAG","value:"+value+hoursData.unit);
                return decimalFormat.format(value) +"%";
            }
        });
        lyAxis.setTextSize(25f);//左侧文字大小
//右y轴配置
        YAxis ryAxis = chart.getAxisRight();
        ryAxis.setEnabled(false);//是否可用
//标签配置
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormSize(20f);
        legend.setTextSize(35f);
        legend.setFormLineWidth(5f);
        legend.setTextColor(Color.WHITE);
        legend.setPosition(ABOVE_CHART_LEFT);
        legend.setEnabled(true);//是否可用
    }
}
