package com.bushra.designingxmlusingjavaclass;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondActivity extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutParamsM2,layoutParamsMW,layoutParamsMH,layoutParamsR2;
    CircleImageView imageView;
    TextView name,gender,speciality,birthDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        scrollView=findViewById(R.id.scroll_view2);
        layoutParamsM2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsMW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsMH = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsR2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        scrollView.addView(linearLayout,layoutParamsM2);
        imageView = new CircleImageView(this);
        imageView.setImageBitmap((Bitmap) getIntent().getExtras().get("img"));
        imageView.setPadding(0,100,0,0);

        linearLayout.addView(imageView);
        name=new TextView(this);
        name.setText("Your name is "+getIntent().getExtras().get("name").toString());
        name.setPadding(0,50,0,0);
        name.setTextColor(Color.BLACK);
        linearLayout.addView(name,layoutParamsR2);
        gender=new TextView(this);
        gender.setText("Your gender is "+getIntent().getExtras().get("gender"));
        gender.setPadding(0,50,0,0);
        gender.setTextColor(Color.BLACK);
        linearLayout.addView(gender,layoutParamsR2);
        speciality= new TextView(this);
        speciality.setText("Your speciality is "+getIntent().getExtras().get("speciality"));
        speciality.setPadding(0,50,0,0);
        speciality.setTextColor(Color.BLACK);
        linearLayout.addView(speciality,layoutParamsR2);
        birthDate= new TextView(this);
        birthDate.setText("Your Birth Date is "+getIntent().getExtras().get("day")+"/"+
                getIntent().getExtras().get("month")+"/"+
                getIntent().getExtras().get("year"));
        birthDate.setPadding(0,50,0,0);
        birthDate.setTextColor(Color.BLACK);
        linearLayout.addView(birthDate,layoutParamsR2);


    }
}
