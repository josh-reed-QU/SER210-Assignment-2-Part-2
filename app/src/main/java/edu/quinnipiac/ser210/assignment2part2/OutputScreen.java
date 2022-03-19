/*
    // Authors: Josh Reed, Andrew Matos
    // Assignment 2 Part 2 - Translate App
    // Due Date: March 18, 2022
 */
package edu.quinnipiac.ser210.assignment2part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OutputScreen extends AppCompatActivity {

    //Key for saving the state of the TextView
    private static final String ORIGINAL_TEXT_STATE = "";
    private static final String TRANSLATED_TEXT_STATE = "";

    // The TextView where we will show results
    private TextView originalText;
    private TextView translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);

        // takes the inputted text from the first screen and displays it on the second screen
        Intent intent = getIntent();
        String textIn = intent.getStringExtra("input");

        TextView originalText = (TextView) findViewById(R.id.originalText);
        originalText.setText(textIn);

        translatedText = (TextView) findViewById(R.id.translatedText);

        new TranslateActivity(textIn, translatedText).execute();

        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null){
            originalText.setText(savedInstanceState.getString(ORIGINAL_TEXT_STATE));
            translatedText.setText(savedInstanceState.getString(TRANSLATED_TEXT_STATE));
        }
    }
    /**
     * Saves the contents of the TextView to restore on configuration change.
     * @param outState The bundle in which the state of the activity is saved when
     * it is spontaneously destroyed.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(ORIGINAL_TEXT_STATE, originalText.getText().toString());
        outState.putString(TRANSLATED_TEXT_STATE, translatedText.getText().toString());
    }
}