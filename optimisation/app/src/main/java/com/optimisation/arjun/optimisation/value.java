package com.optimisation.arjun.optimisation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.bloder.magic.view.MagicButton;
import me.toptas.fancyshowcase.FancyShowCaseView;

public class value extends AppCompatActivity implements View.OnClickListener {
    EditText a_1, a_2, a_3, a_4;
    MediaPlayer as;
//    Button but;
    TextView rdburman;
    Button shw_3;
    FancyShowCaseView mFancyShowCaseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        a_1 = (EditText) findViewById(R.id.a_1);
        as=MediaPlayer.create(this,R.raw.reverse);
        a_2 = (EditText) findViewById(R.id.a_2);
        a_3 = (EditText) findViewById(R.id.a_3);
        a_4 = (EditText) findViewById(R.id.a_4);
//        but = (Button) findViewById(R.id.but);
        shw_3 = (Button) findViewById(R.id.shw_3);
        shw_3.setOnClickListener(this);
        MagicButton btnYoutube=(MagicButton) findViewById(R.id.magic_button_youtube);


//        rdburman=(TextView)findViewById(R.id.textView9);
//        rdburman.setPaintFlags(rdburman.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                btnYoutube.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent abc = new Intent(value.this, calculation.class);
                    abc.putExtra("a_1", Float.parseFloat(String.valueOf(a_1.getText())));
                    abc.putExtra("a_2", Float.parseFloat(String.valueOf(a_2.getText())));
                    abc.putExtra("a_3", Float.parseFloat(String.valueOf(a_3.getText())));
                    abc.putExtra("a_4", Integer.parseInt(String.valueOf(a_4.getText())));
                    as.start();
                    startActivity(abc);
                } catch (Exception e) {
                    Toast.makeText(value.this, "Error: Check all the values that are entered.", Toast.LENGTH_LONG).show();
                }
            }
        });


//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    Intent abc = new Intent(value.this, calculation.class);
//                    abc.putExtra("a_1", Float.parseFloat(String.valueOf(a_1.getText())));
//                    abc.putExtra("a_2", Float.parseFloat(String.valueOf(a_2.getText())));
//                    abc.putExtra("a_3", Float.parseFloat(String.valueOf(a_3.getText())));
//                    abc.putExtra("a_4", Integer.parseInt(String.valueOf(a_4.getText())));
//                    startActivity(abc);
//                } catch (Exception e) {
//                    Toast.makeText(value.this, "Error: Check all the values that are entered.", Toast.LENGTH_LONG).show();
//                }
//            }
//
//
//        });


//    public Float a_1() {
//        return Float.parseFloat(String.valueOf(a_1.getText()));
//    }
//
//    public Float a_2() {
//        return Float.parseFloat(String.valueOf(a_2.getText()));
//    }
//
//    public Float a_3() {
//        return Float.parseFloat(String.valueOf(a_3.getText()));
//    }
//
//    public Integer a_4() {
//        return Integer.parseInt(String.valueOf(a_4.getText()));
//    }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shw_3:
                showWithAnim(v);
                break;
        }
    }
    private void showWithAnim(View v){
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        Animation anm= AnimationUtils.loadAnimation(this,R.anim.slide_in_bottom);
        final FancyShowCaseView fancy= new FancyShowCaseView.Builder(this)
                .title("▫️ Discount rate in percentage example: 11 \n▫️ Taxation rate in percentage example: 30 \n▫️ Profitability rate in percentage example: 28 \n▫️ Mine Life in years example: 25 \n\nYou are good to go \uD83D\uDC4D")
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
