package com.example.abbysflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView exitPageImageView= findViewById(R.id.flashcard_cancel_button);
        exitPageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("debug3", "puppy");
                finish();
            }
        });

        ImageView savePageImageView= findViewById(R.id.flashcard_save_button);
        savePageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                String inputQuestion=((EditText)findViewById(R.id.flashcard_question_edit_text)).getText().toString();
                Log.d("debug1", inputQuestion);
                String inputAnswer=((EditText)findViewById(R.id.flashcard_answer_edit_text)).getText().toString();
                Log.d("debug1", inputAnswer);
                data.putExtra("QUESTION_KEY",inputQuestion);
                data.putExtra("ANSWER_KEY", inputAnswer);
                setResult(RESULT_OK,data);
                finish();
            }
        });

    }
}