package com.bushra.designingxmlusingjavaclass;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutParamsM2,layoutParamsMW,layoutParamsMH,layoutParamsR2;
    CircleImageView imageView;
    Button cameraBtn,dateBtn,sendBtn;
    Bitmap bm;
    EditText nameET;
    RadioGroup gender;
    String genderType;
    RadioButton maleBtn,femaleBtn;
    Spinner dropDownList;
    ArrayList<String> speciality;
    ArrayAdapter<String> arrayAdapter;
    String specialityName;
    int yearOB,monthOB,dayOB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView=findViewById(R.id.scroll_view);
        layoutParamsM2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsMW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsMH = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsR2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        scrollView.addView(linearLayout,layoutParamsM2);
        imageView = new CircleImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        linearLayout.addView(imageView);
        cameraBtn = new Button(this);
        cameraBtn.setText("Camera");
        linearLayout.addView(cameraBtn,layoutParamsR2);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(i,0);
            }
        });

        nameET=new EditText(this);
        nameET.setHint("your name...");
        linearLayout.addView(nameET,layoutParamsMW);

        gender=new RadioGroup(this);
        gender.setOrientation(RadioGroup.HORIZONTAL);
        gender.setGravity(Gravity.CENTER);
        maleBtn=new RadioButton(this);
        femaleBtn=new RadioButton(this);
        maleBtn.setId(R.id.male_btn);
        femaleBtn.setId(R.id.female_btn);
        maleBtn.setText("male");
        femaleBtn.setText("female");
        gender.addView(maleBtn,layoutParamsR2);
        gender.addView(femaleBtn,layoutParamsR2);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==maleBtn.getId())
                    genderType="male";
                else if (checkedId==femaleBtn.getId())
                    genderType="female";
            }
        });

        linearLayout.addView(gender,layoutParamsMW);

        dropDownList = new Spinner(this);
        speciality = new ArrayList<>();
        speciality.add("IT");
        speciality.add("IS");
        speciality.add("CS");
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,speciality);
        dropDownList.setAdapter(arrayAdapter);
        linearLayout.addView(dropDownList);

        dropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                specialityName=dropDownList.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dateBtn= new Button(this);
        dateBtn.setText("Select Your Birth Date");
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog= new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        dateBtn.setText(day+"/"+(month+1)+"/"+year);
                        yearOB=year;
                        monthOB=month+1;
                        dayOB=day;
                    }
                },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });
        linearLayout.addView(dateBtn);

        sendBtn = new Button(this);
        sendBtn.setText("Send");
        linearLayout.addView(sendBtn,layoutParamsR2);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bm == null)
                {
                    Toast.makeText(MainActivity.this,"Please capture an image",Toast.LENGTH_SHORT).show();
                }
                    else if(nameET.getText().toString().matches(""))
                {
                    Toast.makeText(MainActivity.this,"Please insert your name",Toast.LENGTH_SHORT).show();

                }
                else if(genderType==null)
                {
                    Toast.makeText(MainActivity.this,"Please select your gender",Toast.LENGTH_SHORT).show();

                }
                else if(yearOB==0 || monthOB==0 || dayOB==0)
                {
                    Toast.makeText(MainActivity.this,"Please select your birth date",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent i = new Intent(MainActivity.this,SecondActivity.class);
                    i.putExtra("img",bm);
                    i.putExtra("name",nameET.getText().toString());
                    i.putExtra("gender",genderType);
                    i.putExtra("speciality",specialityName);
                    i.putExtra("year",yearOB);
                    i.putExtra("month",monthOB);
                    i.putExtra("day",dayOB);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0 && resultCode==RESULT_OK && data != null)
        {

            bm=(Bitmap) data.getExtras().get("data");

            imageView.setImageBitmap(bm);

        }
        else
        {
            Toast.makeText(MainActivity.this,"no Img",Toast.LENGTH_LONG).show();
        }
    }

}
