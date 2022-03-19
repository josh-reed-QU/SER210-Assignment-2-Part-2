/*
    // Authors: Josh Reed, Andrew Matos
    // Assignment 2 Part 2 - Translate App
    // Due Date: March 18, 2022
 */
package edu.quinnipiac.ser210.assignment2part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class InputScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Spinner spinner = (Spinner) findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // creates an instance of onItemSelectedListener and assigns it to the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setAdapter(adapter);

         */

        setContentView(R.layout.activity_input_screen);
    }

    // called when the translate button is clicked
    public void onTranslate(View view) {
        Intent intent = new Intent(this, OutputScreen.class);
        EditText input = (EditText) findViewById(R.id.textInput);
        // gets text inputted into the edit text on the first screen
        String textInput = input.getText().toString();
        intent.putExtra("input", textInput);

        // starts second activity after the input is stored in the intent
        startActivity(intent);
    }
}