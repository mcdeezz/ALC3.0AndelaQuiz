package com.example.micha.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Welcome extends Activity {
    Button startButton = null;
    RadioButton android = null, webDev = null;
    EditText nameInput = null;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        hideNavigationBar();
        startButton = findViewById(R.id.btn_start);
        nameInput = findViewById(R.id.name_view);
        android = findViewById(R.id.rad_android);
        webDev = findViewById(R.id.rad_web);
        startButton.setOnClickListener(new View.OnClickListener() {
            String category;
            @Override
            public void onClick(View view) {
                String nameOfPart = nameInput.getText().toString();
                getCategory(android.isChecked(), webDev.isChecked());
                if (!nameOfPart.equals("")) {
                    if (android.isChecked() || webDev.isChecked()) {
                        Intent intent = new Intent(Welcome.this, QuizContent.class);
                        intent.putExtra("NAME",nameOfPart );
                        intent.putExtra("CATEGORY", category);
                        Welcome.this.startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.androidOrWebToast), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (android.isChecked() || webDev.isChecked()) {
                        Toast.makeText(getApplicationContext(), getString(R.string.nameOnlyToast), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.allToast), Toast.LENGTH_LONG).show();
                    }

                }
            }
            public void getCategory(boolean android, boolean web){
                if(android){
                    category = "Android";
                }else if(web){
                    category = "Web development";
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    private void hideNavigationBar() {

        this.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
    }
}
