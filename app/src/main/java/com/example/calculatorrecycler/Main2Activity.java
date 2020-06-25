package com.example.calculatorrecycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculatorrecycler.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Button button_calc;
    Button button_share;
        TextView text_for_result;
    Double res;

    com.example.calculator.MainAdapter adapter;
    ArrayList<String> listResult = new ArrayList<>();


    public static final int REQUEST_CODE = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        adapter = new com.example.calculator.MainAdapter(listResult);

        button_calc = findViewById(R.id.button_calc);

        button_share = findViewById(R.id.button_share);

        text_for_result = findViewById(R.id.text_for_result);

        button_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_calculator = new Intent(Main2Activity.this, MainActivity.class);
                startActivityForResult(intent_calculator, REQUEST_CODE);
            }
        });
        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (res != null){
                    Intent intent_share = new Intent();
                    intent_share.setAction(Intent.ACTION_SEND);
                    intent_share.setType("text/plain");
                    intent_share.putExtra(Intent.EXTRA_SUBJECT, "Result: " + res);
                    startActivity(Intent.createChooser(intent_share, "MainActivity"));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            res = data.getDoubleExtra("Result", 0);

//            listResult.add("Result: " + res);
//            adapter.notifyDataSetChanged();

            text_for_result.setText(String.valueOf(res));
        }
    }
}