package com.example.micha.quiz;

public class Android {
   private String[] androidQuestions = {
            "How many versions of the Android operating system has Google released since 2008?",
            "How many mobile phones are powered by Android operating system?",
            "How much did google pay to acquire Android inc?",
            "When did google buy Android inc?",
            "What is the name of the first ever smartphone to run on the Android operating system?"
    };
   private String[] androidAnswer = {
            "24",
           "HTC DreamT_Mobile G1",
            "More than one billion",
           "Cupcake-Android 1.5Donut-Android 1.6Honeycomb-Android 3.0",
           "Cameras",
           "Samsung",
           "50 million",
           "Alphabetical",
            "Male",
            "2005"
    };
   String[] userAnswers = new String[10];
   private String[] androidOption1 = {
            "25",
            "More than three billion",
            "60 million",
            "2002",
            "Samsung"
    };
   private String[] androidOption2 = {
            "26",
            "More than seven billion",
            "40 million",
            "2006",
            "Sony"
    };

    public String[] getAnswers(){
        return androidAnswer;
    }

}
