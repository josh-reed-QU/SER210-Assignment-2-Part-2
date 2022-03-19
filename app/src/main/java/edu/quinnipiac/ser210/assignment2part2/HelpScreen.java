/*
    // Authors: Josh Reed, Andrew Matos
    // Assignment 2 Part 2 - Translate App
    // Due Date: March 18, 2022
 */
package edu.quinnipiac.ser210.assignment2part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HelpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        Intent intent = getIntent();

        // sets background color to match the previous screen
        int color = intent.getIntExtra("backgroundColor", Color.WHITE);
        View temp = findViewById(R.id.helpText);
        View parent = temp.getRootView();
        parent.setBackgroundColor(color);
    }
}