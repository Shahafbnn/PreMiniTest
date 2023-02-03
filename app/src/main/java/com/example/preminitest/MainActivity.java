package com.example.preminitest;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener {
    private EditText etInput;
    private Button btnSplit;
    private SeekBar skSize;
    private TextView[] tvLetters;
    private String inputted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        btnSplit = findViewById(R.id.btnSplit);
        skSize = findViewById(R.id.skSize);

        skSize.setMin(1);
        skSize.setMax(60);

        skSize.setOnSeekBarChangeListener(this);

        etInput.addTextChangedListener(this);
        inputted = "";

        tvLetters = new TextView[6];

        int resID;
        for(int i = 0; i < 6; i++){
            resID = getResources().getIdentifier("textView" + (i + 1) , "id", getPackageName());
            tvLetters[i] = findViewById(resID);
        }

        View.OnClickListener lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputted == null) return;
                int i;
                char[] inputtedArray = inputted.toCharArray();
                int min = Math.min(6, inputted.length());
                for(i = 0; i < min; i++){
                    if(tvLetters[i] != null) tvLetters[i].setText("" + inputtedArray[i]);
                }
                for(; i < 6; i++){
                    if(tvLetters[i] != null) tvLetters[i].setText("");
                }
            }
        };
        btnSplit.setOnClickListener(lis);


    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.toString().equals("")) return;
        inputted = s.toString();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.v("onProgressChanged", "tvLetters: " + Arrays.toString(tvLetters));

        for(int i = 0; i < 6; i++){
            Log.v("onProgressChanged", "progress: " + progress);
            if(tvLetters[i] != null) tvLetters[i].setTextSize(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}