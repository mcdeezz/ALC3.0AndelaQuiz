package com.example.micha.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    TextView display_name, display_category, display_score, display_answers, display_correct_answers;
    String name, category, score, result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        init();
        Android objAndroid = new Android();
        Web objWeb = new Web();
        name = getIntent().getStringExtra("NAME" );
        category = getIntent().getStringExtra("CATEGORY");
        score =  getIntent().getStringExtra("SCORE");
        result = getIntent().getStringExtra("ANSWERS");
        display_name.setText(getString(R.string.result_input_name , name));
        display_category.setText(getString(R.string.result_input_category , category));
        display_score.setText(getString(R.string.result_input_score , score));
        display_answers.setText(getString(R.string.result_input_answers , result));
        if(category.equalsIgnoreCase("Android"))
        display_correct_answers.setText(getString(R.string.result_input_correct , displayAnswers(objAndroid.getAnswers()) ));
        else
            display_correct_answers.setText(getString(R.string.result_input_correct , displayAnswers(objWeb.getAnswers())));
    }
    public void init(){
        display_name = findViewById(R.id.display_name);
        display_category = findViewById(R.id.display_category);
        display_score = findViewById(R.id.display_score);
        display_answers = findViewById(R.id.display_result);
        display_correct_answers = findViewById(R.id.display_right_answers);
    }
    public String displayAnswers(String[] aray){
        String ans = "";
        for(int i = 0; i < aray.length; i++){
            ans += aray[i] + "\n";
        }
        return ans;
    }
}
