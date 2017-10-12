package com.example.krystynastepien.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

    }


    public void onClick(View view) {
        EditText et = (EditText) findViewById(R.id.editText);
        et.setText("");
    }

public void onClickChangeText(View view){
TextView tv = (TextView) findViewById(R.id.txtLab);
    EditText et = (EditText) findViewById(R.id.editText);
    String imie = et.getText().toString();
    tv.setText("witaj " + imie);
}

public void onClickGenerateNumber(View view){
    TextView tv = (TextView) findViewById(R.id.txtLab);
    Random random = new Random();
    int min = 0;
    int max = 100;
    int i = random.nextInt(max - min + 1) + min;
    String liczba = String.valueOf(i);
    tv.setText(liczba);
}


public void onClickResetText(View view){
   TextView tv = (TextView) findViewById(R.id.txtLab);
    tv.setText("");
}


}
