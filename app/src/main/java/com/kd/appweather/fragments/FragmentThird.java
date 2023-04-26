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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.kd.appweather.R;
import com.kd.appweather.beans.FengSu;
import com.kd.appweather.beans.YuLiang;
import com.kd.appweather.databinding.ActivityFirstBinding;
import com.kd.appweather.databinding.ActivityThirdBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.components.Legend.LegendPosition.ABOVE_CHART_LEFT;

public class FragmentThird extends Fragment {
    ActivityThirdBinding binding;
    LineChart fschart,ylchart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(binding==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.activity_third,container,false);
            fschart = binding.fschart;
            ylchart = binding.ylchart;
            initView(fschart,"m/s");
            initView(ylchart,"mm");
        }
        if(fs!=null){
            binding.setFs(fs);
        }
        if(yl!=null){
            binding.setYl(yl);
        }
        if(yls!=null){
            updateYl(yls);
        }
        if(fss!=null){
            updateFs(fss);
        }
        return binding.getRoot();
    }
    String fs ,yl;
    public void updateFs(String fs){
        this.fs = fs;
        if(binding!=null){
            binding.setFs(fs);
        }
    }
    public void updateYl(String yl){
        this.yl = yl;
        if(binding!=null){
            binding.setYl(yl);
        }
    }
    List<YuLiang> yls;
    List<FengSu> fss;
    public void updateYl( List<YuLiang> yls) {
        this.yls = yls;
        if(ylchart==null){
            return;
        }
        final ArrayList<Entry> values = new ArrayList<>();
        final ArrayList<String> xLabers = new ArrayList<>();
        for(int i=0;i<yls.size();i++){
            values.add(yls.get(i).getEntry(i));
            xLabers.add(yls.get(i).getHour());
        }


       /* ArrayList<Entry> values =new ArrayList<>();

        values.add(new Entry(1,2.5f));

        values.add(new Entry(2, 2.4f));

        values.add(new Entry(3, 2.7f));

        values.add(new Entry(4, 3.1f));

        values.add(new Entry(5, 3.60f));

        values.add(new Entry(6,2.4f));

        values.add(new Entry(7,3.1f));

        values.add(new Entry(8,1.9f));

        values.add(new Entry(9,1.8f));

        values.add(new Entry(10,2.8f));

        values.add(new Entry(11,2.2f));

        values.add(new Entry(12,1.5f));*/

      /*  LimitLine llXAxis = new LimitLine(1.8f, "");

        llXAxis.setLineWidth(2f);

        llXAxis.enableDashedLine(10f, 0f, 0f);

        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);

        llXAxis.setTextSize(10f);

        lyAxis.addLimitLine(llXAxis);*/

        LineDataSet lineDataSet = new LineDataSet(values, "雨量曲线图");
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        lineDataSet.setLineWidth(1.0f);

        lineDataSet.setCircleSize(2.0f);

        lineDataSet.setColor(Color.WHITE);

        lineDataSet.setDrawValues(true); // 是否在点上绘制Value

        lineDataSet.setValueTextColor(Color.WHITE);

        lineDataSet.setValueTextSize(20f);

        lineDataSet.setValueFormatter(new IValueFormatter() {

            @Override

            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                return decimalFormat.format(value);

            }

        });

        ylchart.setExtraBottomOffset(10);

        ylchart.setExtraRightOffset(10);

        ylchart.setExtraLeftOffset(10);

        LineData lineData = new LineData(lineDataSet);

        ylchart.setEnabled(true);

        ylchart.animateX(4000);

        ylchart.setDescription(null);

        ylchart.setData(lineData);
        ylchart.getXAxis().setValueFormatter(new IAxisValueFormatter() {

            @Override

            public String getFormattedValue(float value, AxisBase axis) {

                String hour = "";

                if(xLabers.size()>value){
                    hour = xLabers.get((int)value)+"时";

                }
                return hour;

            }

        });


    }
    public void updateFs( List<FengSu> fss) {
        this.fss = fss;
        if(fschart==null){
            return;
        }
        final ArrayList<Entry> values = new ArrayList<>();
        final ArrayList<String> xLabers = new ArrayList<>();
        for(int i=0;i<fss.size();i++){
            values.add(fss.get(i).getEntry(i));
            xLabers.add(fss.get(i).getHour());
        }


       /* ArrayList<Entry> values =new ArrayList<>();

        values.add(new Entry(1,2.5f));

        values.add(new Entry(2, 2.4f));

        values.add(new Entry(3, 2.7f));

        values.add(new Entry(4, 3.1f));

        values.add(new Entry(5, 3.60f));

        values.add(new Entry(6,2.4f));

        values.add(new Entry(7,3.1f));

        values.add(new Entry(8,1.9f));

        values.add(new Entry(9,1.8f));

        values.add(new Entry(10,2.8f));

        values.add(new Entry(11,2.2f));

        values.add(new Entry(12,1.5f));*/

      /*  LimitLine llXAxis = new LimitLine(1.8f, "");

        llXAxis.setLineWidth(2f);

        llXAxis.enableDashedLine(10f, 0f, 0f);

        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);

        llXAxis.setTextSize(10f);

        lyAxis.addLimitLine(llXAxis);*/

        LineDataSet lineDataSet = new LineDataSet(values, "风速曲线图");

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        lineDataSet.setLineWidth(1.0f);

        lineDataSet.setCircleSize(2.0f);

        lineDataSet.setColor(Color.WHITE);

        lineDataSet.setDrawValues(true); // 是否在点上绘制Value

        lineDataSet.setValueTextColor(Color.WHITE);

        lineDataSet.setValueTextSize(20f);

        lineDataSet.setValueFormatter(new IValueFormatter() {

            @Override

            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                return decimalFormat.format(value);

            }

        });

        fschart.setExtraBottomOffset(10);

        fschart.setExtraRightOffset(10);

        fschart.setExtraLeftOffset(10);

        LineData lineData = new LineData(lineDataSet);

        fschart.setEnabled(true);

        fschart.animateX(4000);

        fschart.setDescription(null);

        fschart.setData(lineData);
        fschart.getXAxis().setValueFormatter(new IAxisValueFormatter() {

            @Override

            public String getFormattedValue(float value, AxisBase axis) {

                String hour = "";

                if(xLabers.size()>value){
                    hour = xLabers.get((int)value)+"时";

                }
                return hour;

            }

        });


    }

    DecimalFormat decimalFormat = new DecimalFormat("0.0");
    public void initView(LineChart chart, final String unit) {

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
        XAxis xAxis;

        YAxis lyAxis;
        xAxis = chart.getXAxis();

        xAxis.setEnabled(true);//是否可用

        xAxis.setDrawLabels(true);//是否显示数值

        xAxis.setDrawAxisLine(false);//是否显示坐标线

        xAxis.setAxisLineColor(Color.WHITE);//设置坐标轴线的颜色

        xAxis.setAxisLineWidth(0.8f);//设置坐标轴线的宽度

        xAxis.setDrawGridLines(false);//是否显示竖直风格线

        xAxis.setTextColor(Color.WHITE);//X轴文字颜色

        xAxis.setGridColor(Color.WHITE);//网格线颜色

        xAxis.setTextSize(30f);//X轴文字大小

       /* final ArrayList<String> values = new ArrayList<>();

        values.add(19+"");

        values.add(20+"");

        values.add(21+"");

        values.add(22+"");

        values.add(23+"");

        values.add(00+"");

        values.add(01+"");

        values.add(02+"");

        values.add(03+"");

        values.add(04+"");

        values.add(05+"");

        values.add(06+"");*/





        xAxis.setLabelCount(12, false);

        //xAxis.setGranularity(1f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴文字显示位置


        xAxis.setSpaceMin(0.5f);//左空白区大小

        xAxis.setSpaceMax(0.5f);//右空白区大小

//左y轴配置

        lyAxis = chart.getAxisLeft();

        lyAxis.setLabelCount(5, false);

        lyAxis.setSpaceMin(0.5f);//左空白区大小

        lyAxis.setSpaceMax(0.5f);//右空白区大小

        lyAxis.setEnabled(true);//是否可用

        lyAxis.setDrawLabels(true);//是否显示数值

        lyAxis.setDrawAxisLine(false);//是否显示坐标线

        lyAxis.setDrawGridLines(true);//是否显示水平网格线

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

                //  Log.i("TAG","value:"+value);

                // Log.i("TAG","unit:"+hoursData.unit);

                return decimalFormat.format(value)+unit;

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
