package com.example.abbysflashcardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
      TextView flashcardQuestion;
      TextView flashcardAnswer;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int cardIndex = 0;

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
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            Flashcard firstCard = allFlashcards.get(0);
            flashcardQuestion.setText(firstCard.getQuestion());
            flashcardAnswer.setText(firstCard.getAnswer());


        }

        findViewById(R.id.flashcard_next_question_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFlashcards.size() == 0)
                    return;
                cardIndex += 1;

               if(cardIndex>=allFlashcards.size()){
                   Snackbar.make(view,
                           "You've reached the end of the cards! Going back to the start.",
                                   Snackbar.LENGTH_SHORT)
                           .show();
                   cardIndex=0;// reset index so user can go back to the beginning of cards
               }
                Flashcard currentCard = allFlashcards.get(cardIndex);
                flashcardQuestion.setText(currentCard.getQuestion());
                flashcardAnswer.setText(currentCard.getAnswer());

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

                    flashcardDatabase.insertCard(new Flashcard(questionString, answerString));
                    allFlashcards = flashcardDatabase.getAllCards();
                }
            }
        }

}
