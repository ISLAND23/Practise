package com.example.readyproj.module.datastorage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.readyproj.R;

public class InternaltorageActivity extends Activity {
    private EditText inputTx;
    private Button savaBt,readBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        inputTx=findViewById(R.id.phone_text);
        savaBt=findViewById(R.id.SaveButton);
        readBt=findViewById(R.id.LoadButton);
        savaBt.setOnClickListener(new ButtonListener());
        readBt.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.SaveButton:
                  //  String savaInfo=inputTx.getText().toString().
                    break;
                case R.id.LoadButton:
                    break;
            }
        }
    }
}
