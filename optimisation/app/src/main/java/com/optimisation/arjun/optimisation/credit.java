package com.optimisation.arjun.optimisation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class credit extends AppCompatActivity {

    private Animation animation;
    LinearLayout abcdf;
    private static final int REQUEST_CALL = 1;
    Button call_1,call_2,fb_1,fb_2,in_1,in_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.credis);
        abcdf=(LinearLayout)findViewById(R.id.abcdf);
        abcdf.startAnimation(animation);

        call_1 = (Button) findViewById(R.id.call_1);
        fb_1 = (Button) findViewById(R.id.fb_1);
        fb_2 = (Button) findViewById(R.id.fb_2);
        in_1 = (Button) findViewById(R.id.in_1);
        in_2=(Button)findViewById(R.id.in_2);
        call_2 = (Button) findViewById(R.id.call_2);
        fb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookintent=openFacebook(credit.this);
                startActivity(facebookintent);


            }
        });
        fb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookinten=openFaceboo(credit.this);
                startActivity(facebookinten);


            }
        });
        in_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookintents=openLink(credit.this);
                startActivity(facebookintents);


            }
        });
        in_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebo=openLinked(credit.this);
                startActivity(facebo);


            }
        });
        call_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        call_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });


    }
    public static Intent openFacebook(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/100006113351806"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/jagdish.mohnot"));
        }


    }
    public static Intent openLink(Context context){
        return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/arjun-mohnot-7a9154148/"));



    }
    public static Intent openFaceboo(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/100003507178547"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/arjun.roockz"));
        }


    }
    public static Intent openLinked(Context context){
        return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/dr-jk-mohnot-516489a0/"));



    }





    private void makePhoneCall() {
        String number = "7733993964";
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(credit.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(credit.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(credit.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
