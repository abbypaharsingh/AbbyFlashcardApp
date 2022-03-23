package com.example.abbysflashcardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
      TextView flashcardQuestion;
      TextView flashcardAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardQuestion = findViewById(R.id.flashcard_question_textview);
        TextView flashcardAnswer = findViewById(R.id.flashcard_answer_textview);
        flashcardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardQuestion.setVisibility(View.INVISIBLE);
                flashcardAnswer.setVisibility(View.VISIBLE);
                // Do something here

            }
        });
        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);
                // Do something here

            }
        });
        ImageView addQuestionImageView = findViewById(R.id.flashcard_add_question_button);
        addQuestionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                startActivityForResult(intent, 100);

            }
        });
        }

     @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
         TextView flashcardQuestion = findViewById(R.id.flashcard_question_textview);
         TextView flashcardAnswer = findViewById(R.id.flashcard_answer_textview);
          if (requestCode == 100) {
                //get data
                if (data != null){ //check if there is an intent
                    String questionString= data.getExtras().getString("QUESTION_KEY");
                    String answerString= data.getExtras().getString("ANSWER_KEY");
                    Log.d("debug", questionString);
                    Log.d("debug", answerString);
                    flashcardQuestion.setText(questionString);
                    flashcardAnswer.setText(answerString);

                }
            }
        }
}
