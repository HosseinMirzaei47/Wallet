package com.example.hossein.wallet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.core.ui.DataArea;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    private static final String TAG = "jalil";
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();

    private AnyChartView anyChartView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        names = getArguments().getStringArrayList("names");
        scores = getArguments().getIntegerArrayList("scores");

        /*Log.i(TAG, String.valueOf(names.size()));
        Log.i(TAG, String.valueOf(scores.size()));*/
        return inflater.inflate(R.layout.fragment_month_expenses, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createPieChart(view);

    }

    private void createPieChart(View view) {

        anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));

        Pie pie = AnyChart.pie();
        ArrayList<DataEntry> data = new ArrayList<>();

        if (names.size() == scores.size())
            for (int i = 0; i < names.size(); i++)
                data.add(new ValueDataEntry(names.get(i), scores.get(i)));


        pie.data(data);

        pie.title("Smelly cat");

        pie.legend()
                .position("left")
                .itemsLayout(LegendLayout.VERTICAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);

    }
}
