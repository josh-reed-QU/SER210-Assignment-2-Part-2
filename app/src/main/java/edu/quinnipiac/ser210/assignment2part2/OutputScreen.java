/*
    // Authors: Josh Reed, Andrew Matos
    // Assignment 2 Part 2 - Translate App
    // Due Date: March 18, 2022
 */
package edu.quinnipiac.ser210.assignment2part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OutputScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);

        // takes the inputted text from the first screen and displays it on the second screen
        Intent intent = getIntent();
        String textIn = intent.getStringExtra("input");

        TextView originalText = (TextView) findViewById(R.id.originalText);
        originalText.setText(textIn);
    }
}