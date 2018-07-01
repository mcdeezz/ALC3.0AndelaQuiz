package com.example.micha.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizContent extends AppCompatActivity {
    String name, category;
    String answer1, answer2 = "", answer5, answer3, answer4 = "", answer6, answer7, answer8, answer9, answer10, answersGotWrong = " \n", wrongAnswer = "";
    int score = 0, clock = 30;
    RadioButton question1Option1, question1Option2, question1Option3, question1Option4;
    RadioButton question3Option1, question3Option2, question3Option3, question3Option4;
    RadioButton question7Option1, question7Option2, question7Option3, question7Option4;
    RadioButton question10Option1, question10Option2, question10Option3, question10Option4;
    RadioGroup radioGroup1, radioGroup3, radioGroup7, radioGroup10;
    EditText question5Answer, question6Answer, question8Answer, question9Answer;
    CheckBox question2Option1, question2Option2, question2Option3, question2Option4, question4Option1, question4Option2, question4Option3, question4Option4;
    TextView nameView, categoryView, scoreView;
    TextView question1, question2, question3, question4, question5, question6, question7, question8, question9, question10;
    Button submit, compute;
    String[] userAnswers = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_content);
        // Initializing the views
        init();
        // get the extras from the previous activity
        name = getIntent().getStringExtra("NAME");
        category = getIntent().getStringExtra("CATEGORY");
        // Setting values to text views
        nameView.setText(getString(R.string.quizName, name));
        categoryView.setText(getString(R.string.quizCategory, category));
        scoreView.setText(getString(R.string.quizScore, score));
        // if its web
        setTextToViews(category);
        // Start button
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category.equalsIgnoreCase("Android")){
                    Android androidObject = new Android();
                    storeEditViewAnswers();
                    setArrayItems(userAnswers);
                    checkAnswers(userAnswers, androidObject.getAnswers());

                    display(androidObject.getAnswers());
                }else{
                    Web webObject = new Web();
                    storeEditViewAnswers();
                    setArrayItems(userAnswers);
                    checkAnswers(userAnswers, webObject.getAnswers());
                    display(webObject.getAnswers());
                }


            }
        });
        /*
         ** Setting the onclick event handling method to compute the answers
         */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Moving from one activity to another and sending the values
                Intent intent = new Intent(QuizContent.this, Display.class);
                intent.putExtra("NAME", name);
                intent.putExtra("CATEGORY", category);
                intent.putExtra("SCORE", String.valueOf(score));
                intent.putExtra("ANSWERS", answersGotWrong);
                QuizContent.this.startActivity(intent);

            }
        });
    }


    /*
     ** Displaying the output in a toast.
     ** The correct answers will be displayed together with the answers the user got wrong. @param corr
     */
    public void display(String[] corr) {
        String correct = "";

        for (int i = 0; i < corr.length; i++) {
            correct += corr[i] + "\n";
        }
        correct += answersGotWrong + "\n\nScore is : " + score;
        Toast.makeText(this, correct, Toast.LENGTH_LONG).show();
    }

    /*
     ** @param arA, arB Checking users answer to show correct and wrong answers
     */
    public void checkAnswers(String[] arA, String[] arB) {
        for(int i = 0; i < arA.length; i++) {
            if (arA[i].equalsIgnoreCase(arB[i])) {
                score++;
            } else if (question2Option1.isChecked() && question2Option2.isChecked() && i == 1) {
                score++;
            } else if (question4Option1.isChecked() && question4Option2.isChecked() && question4Option3.isChecked() && i == 3) {
                score++;
            } else {
                answersGotWrong += i + ") \t" + arA[i] + "\n";
                wrongAnswer += i + ") \t" + arB[i] + "\n";
            }
        }
    }

    /*
     ** @ param arr
     ** set each user answer to an array so we can compare with the correct answer array
     */
    public void setArrayItems(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0)
                arr[i] = answer1;
            else if (i == 1)
                arr[1] = answer2;
            else if (i == 2)
                arr[i] = answer3;
            else if (i == 3)
                arr[i] = answer4;
            else if (i == 4)
                arr[i] = answer5;
            else if (i == 5)
                arr[i] = answer6;
            else if (i == 6)
                arr[i] = answer7;
            else if (i == 7)
                arr[i] = answer8;
            else if (i == 8)
                arr[i] = answer9;
            else if (i == 9)
                arr[i] = answer10;
        }
    }

    /*
     ** A method for each possible answer and store the answer in a string variable to compare with the array
     * * getting the id of the clicked radiobutton to retrieve its text
     */
    public void storeAnswer1(View view) {
        answer1 = getAnswer(view.getId());
    }

    /*
     ** getting the id of the clicked radiobutton to retrieve its text
     */
    public void storeAnswer3(View view) {
        answer3 = getAnswer(view.getId());
    }

    /*
     ** getting the id of the clicked radiobutton to retrieve its text
     */
    public void storeAnswer7(View view) {
        answer7 = getAnswer(view.getId());
    }

    /*
     ** getting the id of the clicked radiobutton to retrieve its text
     */
    public void storeAnswer10(View view) {
        answer10 = getAnswer(view.getId());
    }

    /* @ param id
     ** saving each user radiobutton selection answer to a global string variable to store in a parallel array for comparison
     */
    public String getAnswer(int id) {
        RadioButton chickedOption = findViewById(id);
        return ((String) chickedOption.getText());
    }

    /* @ param id
     ** saving user checkbox selection answer to a global string variable to store in a parallel array for comparison
     */
    public void storeAnswer2(View view) {
        if (question2Option1.isChecked())
            answer2 += question2Option1.getText();
        if (question2Option2.isChecked())
            answer2 += question2Option2.getText();
        if (question2Option3.isChecked())
            answer2 += question2Option3.getText();
        if (question2Option4.isChecked())
            answer2 += question2Option4.getText();
    }

    /* @ param id
     ** saving user checkbox selection(s) answer to a global string variable to store in a parallel array for comparison
     */
    public void storeAnswer4(View view) {
        if (question4Option1.isChecked())
            answer4 += question4Option1.getText();
        if (question4Option2.isChecked())
            answer4 += question4Option2.getText();
        if (question4Option3.isChecked())
            answer4 += question4Option3.getText();
        if (question4Option4.isChecked())
            answer4 += question4Option4.getText();
    }

    /*
     ** getting the user answer from the edit view
     */
    public void storeEditViewAnswers() {
        answer5 = question5Answer.getText().toString();
        answer6 = question6Answer.getText().toString();
        answer8 = question8Answer.getText().toString();
        answer9 = question9Answer.getText().toString();
    }

    /*
     ** Initializing all the views
     */
    public void init() {
        // the heading text views initialized
        nameView = findViewById(R.id.quizeName);
        categoryView = findViewById(R.id.quizeCategory);
        scoreView = findViewById(R.id.quizeScore);
        // initializing buttons
        compute = findViewById(R.id.btnCompute);
        submit = findViewById(R.id.btnSubmit);
        // The questions text view initialized
        question1 = findViewById(R.id.question1_view);
        question2 = findViewById(R.id.question2_view);
        question3 = findViewById(R.id.question3_view);
        question7 = findViewById(R.id.question7_view);
        question4 = findViewById(R.id.question4_view);
        question5 = findViewById(R.id.question5_view);
        question6 = findViewById(R.id.question6_view);
        question8 = findViewById(R.id.question8_view);
        question9 = findViewById(R.id.question9_view);
        question10 = findViewById(R.id.question10_view);
        // initializing edit views
        question5Answer = findViewById(R.id.question5_edit_view);
        question6Answer = findViewById(R.id.question6_edit_view);
        question8Answer = findViewById(R.id.question8_edit_view);
        question9Answer = findViewById(R.id.question9_edit_view);
        // initializing radiogroups
        radioGroup1 = findViewById(R.id.radiogroup1);
        radioGroup3 = findViewById(R.id.radiogroup3);
        radioGroup7 = findViewById(R.id.radiogroup7);
        radioGroup10 = findViewById(R.id.radiogroup10);
        // radiobutton initialization
        question1Option1 = findViewById(R.id.radio_button_question1_option1);
        question1Option2 = findViewById(R.id.radio_button_question1_option2);
        question1Option3 = findViewById(R.id.radio_button_question1_option3);
        question1Option4 = findViewById(R.id.radio_button_question1_option4);

        question3Option1 = findViewById(R.id.radio_button_question3_option1);
        question3Option2 = findViewById(R.id.radio_button_question3_option2);
        question3Option3 = findViewById(R.id.radio_button_question3_option3);
        question3Option4 = findViewById(R.id.radio_button_question3_option4);

        question7Option1 = findViewById(R.id.radio_button_question7_option1);
        question7Option2 = findViewById(R.id.radio_button_question7_option2);
        question7Option3 = findViewById(R.id.radio_button_question7_option3);
        question7Option4 = findViewById(R.id.radio_button_question7_option4);

        question10Option1 = findViewById(R.id.radio_button_question10_option1);
        question10Option2 = findViewById(R.id.radio_button_question10_option2);
        question10Option3 = findViewById(R.id.radio_button_question10_option3);
        question10Option4 = findViewById(R.id.radio_button_question10_option4);
        // Initializing checkboxes
        question2Option1 = findViewById(R.id.checkbox_question2_option1);
        question2Option2 = findViewById(R.id.checkbox_question2_option2);
        question2Option3 = findViewById(R.id.checkbox_question2_option3);
        question2Option4 = findViewById(R.id.checkbox_question2_option4);

        question4Option1 = findViewById(R.id.checkbox_question4_option1);
        question4Option2 = findViewById(R.id.checkbox_question4_option2);
        question4Option3 = findViewById(R.id.checkbox_question4_option3);
        question4Option4 = findViewById(R.id.checkbox_question4_option4);

    }
    /* @param category
     ** setting questions and answers to views
     */
    public void setTextToViews(String category){
        if(category.equalsIgnoreCase("Android")){
            question1.setText(getString(R.string.android_question1));
            question1Option1.setText(getString(R.string.android_question1_option1));
            question1Option2.setText(getString(R.string.android_question1_option2));
            question1Option3.setText(getString(R.string.android_question1_option3));
            question1Option4.setText(getString(R.string.android_question1_option4));

            question2.setText(getString(R.string.android_question2));
            question2Option1.setText(getString(R.string.android_question2_option1));
            question2Option2.setText(getString(R.string.android_question2_option2));
            question2Option3.setText(getString(R.string.android_question2_option3));
            question2Option4.setText(getString(R.string.android_question2_option4));

            question3.setText(getString(R.string.android_question3));
            question3Option1.setText(getString(R.string.android_question3_option1));
            question3Option2.setText(getString(R.string.android_question3_option2));
            question3Option3.setText(getString(R.string.android_question3_option3));
            question3Option4.setText(getString(R.string.android_question3_option4));

            question4.setText(getString(R.string.android_question4));
            question4Option1.setText(getString(R.string.android_question4_option1));
            question4Option2.setText(getString(R.string.android_question4_option2));
            question4Option3.setText(getString(R.string.android_question4_option3));
            question4Option4.setText(getString(R.string.android_question4_option4));


            question5.setText(getString(R.string.android_question5));
            question6.setText(getString(R.string.android_question6));

            question7.setText(getString(R.string.android_question7));
            question7Option1.setText(getString(R.string.android_question7_option1));
            question7Option2.setText(getString(R.string.android_question7_option2));
            question7Option3.setText(getString(R.string.android_question7_option3));
            question7Option4.setText(getString(R.string.android_question7_option4));

            question8.setText(getString(R.string.android_question8));
            question9.setText(getString(R.string.android_question9));

            question10.setText(getString(R.string.android_question10));
            question10Option1.setText(getString(R.string.android_question10_option1));
            question10Option2.setText(getString(R.string.android_question10_option2));
            question10Option3.setText(getString(R.string.android_question10_option3));
            question10Option4.setText(getString(R.string.android_question10_option4));
        }else{
            question1.setText(getString(R.string.web_question1));
            question1Option1.setText(getString(R.string.web_question1_option1));
            question1Option2.setText(getString(R.string.web_question1_option2));
            question1Option3.setText(getString(R.string.web_question1_option3));
            question1Option4.setText(getString(R.string.web_question1_option4));

            question2.setText(getString(R.string.web_question2));
            question2Option1.setText(getString(R.string.web_question2_option1));
            question2Option2.setText(getString(R.string.web_question2_option2));
            question2Option3.setText(getString(R.string.web_question2_option3));
            question2Option4.setText(getString(R.string.web_question2_option4));

            question4.setText(getString(R.string.web_question4));
            question4Option1.setText(getString(R.string.web_question4_option1));
            question4Option2.setText(getString(R.string.web_question4_option2));
            question4Option3.setText(getString(R.string.web_question4_option3));
            question4Option4.setText(getString(R.string.web_question4_option4));

            question3.setText(getString(R.string.web_question3));
            question3Option1.setText(getString(R.string.web_question3_option1));
            question3Option2.setText(getString(R.string.web_question3_option2));
            question3Option3.setText(getString(R.string.web_question3_option3));
            question3Option4.setText(getString(R.string.web_question3_option4));

            question5.setText(getString(R.string.web_question5));
            question6.setText(getString(R.string.web_question6));

            question7.setText(getString(R.string.web_question7));
            question7Option1.setText(getString(R.string.web_question7_option1));
            question7Option2.setText(getString(R.string.web_question7_option2));
            question7Option3.setText(getString(R.string.web_question7_option3));
            question7Option4.setText(getString(R.string.web_question7_option4));

            question8.setText(getString(R.string.web_question8));
            question9.setText(getString(R.string.web_question9));

            question10.setText(getString(R.string.web_question10));
            question10Option1.setText(getString(R.string.web_question10_option1));
            question10Option2.setText(getString(R.string.web_question10_option2));
            question10Option3.setText(getString(R.string.web_question10_option3));
            question10Option4.setText(getString(R.string.web_question10_option4));
        }
    }

}
