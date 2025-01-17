package com.optimisation.arjun.optimisation;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import static java.lang.Math.pow;

public class calculation extends AppCompatActivity{


    GraphView graph;
    MyHelper myHelper;
    value Value;
    SQLiteDatabase sqLiteDatabase;
    LineGraphSeries<DataPoint> series=new LineGraphSeries<>(new DataPoint[0]);
    LineGraphSeries<DataPoint> ss=new LineGraphSeries<>(new DataPoint[0]);
    LineGraphSeries<DataPoint> sss=new LineGraphSeries<>(new DataPoint[0]);
    TextView text_,text_a,text_11,text_22,rdburman;
    int n,N,i,m,ks;
    float X,Y,M,t,I,GP,r,V,C,Vo,Vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        text_11=(TextView)findViewById(R.id.text_3);
//        rdburman=(TextView)findViewById(R.id.textView9);
//        rdburman.setPaintFlags(rdburman.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        text_22=(TextView)findViewById(R.id.text_4);
        text_a=(TextView)findViewById(R.id.text_2);
        text_=(TextView)findViewById(R.id.text_1);
        graph = (GraphView) findViewById(R.id.graph);
        myHelper=new MyHelper(this);
        Value=new value();

        sqLiteDatabase=myHelper.getWritableDatabase();
        exqButton();

        graph.addSeries(series);
        graph.addSeries(ss);
        graph.addSeries(sss);
        sss.setColor(Color.GREEN);
        ss.setColor(Color.rgb(133,85,37));
        ss.setDrawBackground(true);
        ss.setBackgroundColor(Color.argb(20,255,0,65));
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(17,70,141,255));
        sss.setDrawBackground(true);
        sss.setBackgroundColor(Color.argb(18,140,255,0));



        ss.setTitle("C");
        sss.setTitle("GP");
        series.setTitle("I");

        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(7);
        sss.setDrawDataPoints(true);
        sss.setDataPointsRadius(6);
        ss.setDrawDataPoints(true);
        ss.setDataPointsRadius(5);


//        graph.setTitle("Mining Optimisation");
//        graph.setTitleColor(Color.RED);

        graph.getGridLabelRenderer().setPadding(53);
//        graph.getGridLabelRenderer().setHorizontalAxisTitle(" Mine Life (n) in Years");
        graph.getGridLabelRenderer().setVerticalAxisTitle(" Values of I & C in % of R");
        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(40);
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.WHITE);
        graph.setTitleColor(Color.WHITE);
        graph.getViewport().setXAxisBoundsManual(true);


        //

        graph.getGridLabelRenderer().setHighlightZeroLines(true);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.rgb(195,200,205));
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.rgb(195,200,205));
//        graph.getGridLabelRenderer().setVerticalLabelsVAlign(GridLabelRenderer.VerticalLabelsVAlign.ABOVE);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        graph.getGridLabelRenderer().setGridColor(Color.argb(150,168,173,180));
        graph.getGridLabelRenderer().reloadStyles();
        //
        graph.getViewport().setBackgroundColor(Color.argb(16, 147, 144, 144));
        graph.getViewport().setDrawBorder(true);
        graph.getViewport().setBorderColor(Color.BLACK);






        //

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setTextSize(25);
        graph.getLegendRenderer().setBackgroundColor(Color.rgb(26,29,38));
        graph.getLegendRenderer().setTextColor(Color.WHITE);
        //graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
//        graph.getLegendRenderer().setMargin(30);
        graph.getLegendRenderer().setFixedPosition(605, -11);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxY(200.0);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setMaxX(20.0);

        //


//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//
//
//        LineGraphSeries<DataPoint> ss = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(-3, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 67)
//        });
//        graph.addSeries(ss);
//        ss.setColor(Color.GRAY);
//        ss.setDrawBackground(true);
//        ss.setDrawDataPoints(true);
//        ss.setDataPointsRadius(6);
//        graph.setTitle("Mining Optimisation");
//
    }

    private void exqButton() {
        try {

            float xvals = (getIntent().getFloatExtra("a_1", 0))/100;
            float yvals = (getIntent().getFloatExtra("a_2", 0))/100;
            float zvals = (getIntent().getFloatExtra("a_3", 0))/100;
            int bvals = getIntent().getIntExtra("a_4", 0);
            int avals = 0;

            Y = 1;
            if (avals == 0) {
                for (i = 1; i <= bvals; i++) {
                    V = (float) ((pow((1 + xvals), i) - 1) / (pow((1 + xvals), i) * xvals));
                    I = V * zvals;
                    C = 1 - zvals * ((V / i) + (1 / (1 - yvals)));

                    if (C > 0) {
                        myHelper.insertData(i * 1, I * 100, C * 100, (1 - C) * 100);
                    }

                    X = (float) Math.abs(0.5 - C);
                    if (X < Y) {
                        Y = X;
                        N = i;
                    } else {
                        Y = Y;
                        N = N;
                    }


                }

                text_.setText("" + N + " Years");


            }


            series.resetData(getData());
            ss.resetData(getgross());
            sss.resetData(getprofit());

            for (int ks = 1; ks <= bvals; ks++) {
                myHelper.deletedata("" + ks);
            }


        }

        catch (Exception e){
            Toast.makeText(calculation.this, "Error: Check all the values that are entered.", Toast.LENGTH_LONG).show();
        }
    }



    private DataPoint[] getData() {
    //Read Data from Database
        String [] coloumns={"xValues","yValues"};
        Cursor cursor=sqLiteDatabase.query("MyTable",coloumns,null,null,null,null,"xValues"+" ASC");
        DataPoint [] dp=new DataPoint[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0),cursor.getInt(1));
            if(cursor.getInt(0)==N){
                text_a.setText(""+cursor.getFloat(1)/100+"R");
            }

        }
        return dp;
    }
    private DataPoint[] getgross() {
        //Read Data from Database
        String [] coloumns={"xValues","zValues"};
        Cursor cursor=sqLiteDatabase.query("MyTable",coloumns,null,null,null,null,"xValues"+" ASC");
        DataPoint [] dp=new DataPoint[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0),cursor.getInt(1));
            if(cursor.getInt(0)==N){
                text_11.setText(""+cursor.getFloat(1)/100+"R");
            }

        }
        return dp;
    }

    private DataPoint[] getprofit() {
        //Read Data from Database
        String [] coloumns={"xValues","aValues"};
        Cursor cursor=sqLiteDatabase.query("MyTable",coloumns,null,null,null,null,"xValues"+" ASC");
        DataPoint [] dp=new DataPoint[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0),cursor.getInt(1));
            if(cursor.getInt(0)==N){
                text_22.setText(""+cursor.getFloat(1)/100+"R");
            }

        }
        return dp;
    }




}
