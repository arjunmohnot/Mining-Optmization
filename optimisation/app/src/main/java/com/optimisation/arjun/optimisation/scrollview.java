package com.optimisation.arjun.optimisation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Animatable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import br.com.bloder.magic.view.MagicButton;
import me.toptas.fancyshowcase.FancyShowCaseView;

import java.text.SimpleDateFormat;

public class scrollview extends AppCompatActivity implements View.OnClickListener{
    mycounter myHelper;
    value Value;
    float yl=0;
    float tr = 0;
    float rtt=0;
    SQLiteDatabase sqLiteDatabase;
    FancyShowCaseView mFancyShowCaseView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        TextView textab=(TextView)findViewById(R.id.textab);
        long date=System.currentTimeMillis();
        SimpleDateFormat aa=new SimpleDateFormat("dd MMM yyyy, h:mm a");
        String datestring=aa.format(date);
        textab.setText(""+datestring);
        ExpandableTextView expTv1 = (ExpandableTextView)findViewById(R.id.expand_text_view);
        expTv1.setText(getString(R.string.in_news));
        myHelper=new mycounter(this);
        Value=new value();
        sqLiteDatabase=myHelper.getWritableDatabase();
        exqButton();
//        MagicButton btnYoutube=(MagicButton) findViewById(R.id.magic_button_youtube);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        Button bu =(Button)findViewById(R.id.next_1);
        bu.setOnClickListener(this);
        CollapsingToolbarLayout cool=(CollapsingToolbarLayout)findViewById(R.id.ab12);
        setSupportActionBar(toolbar);
        cool.setTitle("Mining Optimization");
//        btnYoutube.setMagicButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ii=new Intent(scrollview.this,value.class);
//                startActivity(ii);
//            }
//        }

//        bu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent ii=new Intent(scrollview.this,value.class);
//                startActivity(ii);
//            }
//        });
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            cool.setCollapsedTitleTextAppearance(R.style.Text123);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(scrollview.this,MainActivity.class);
                    startActivity(in);

                }
            });


    }

    private void exqButton() {
        yl += 0.01;
        myHelper.insertData(yl * 1);

        String[] coloumns = {"yValues"};
        Cursor cursor = sqLiteDatabase.query("Mytable", coloumns, null, null, null, null, null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            yl += cursor.getInt(0);

        }
        myHelper.insertData(yl * 1);
    }
    private float getData() {
        //Read Data from Database
        String [] coloumns={"yValues"};
        Cursor cursor=sqLiteDatabase.query("Mytable",coloumns,null,null,null,null,null);
        DataPoint [] dp=new DataPoint[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            if(cursor.getInt(0)>=0.01){
                rtt=9;
            }
            else{
                rtt=0;

            }

        }

        return rtt;
    }

    @Override
    public void onClick(View v) {
        tr = getData();
        if (tr > 1) {
            switch (v.getId()) {
                case R.id.next_1:
                    Intent ii=new Intent(scrollview.this,selection.class);
                    startActivity(ii);
                    break;
            }


        } else {
            switch (v.getId()) {
                case R.id.next_1:
                    yl+=1;
                    exqButton();
                    showWithAnim(v);
                    break;
            }
        }
    }
    private void showWithAnim(View v){
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        Animation anm= AnimationUtils.loadAnimation(this,R.anim.slide_in_bottom);
        final FancyShowCaseView fancy= new FancyShowCaseView.Builder(this)
                .title("You are good to go \uD83D\uDC4D")
                .enterAnimation(anim)
                .exitAnimation(anm)
                .build();
        fancy.show();
        anm.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fancy.removeView();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
