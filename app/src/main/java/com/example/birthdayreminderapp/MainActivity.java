package com.example.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    private Button viewButton;
    private Button settingsButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Loads second activity when add button is clicked (Add birthday)
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this, AddBirthday.class);
                startActivity(add);
            }
        });

        // Loads second activity when view saved button is clicked (Saved birthdays)
        viewButton = findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saved = new Intent(MainActivity.this, SavedBirthdays.class);
                startActivity(saved);
            }
        });

        // Loads second activity when settings button is clicked (Settings)
        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, Settings.class);
                startActivity(settings);
            }
        });
    }
}



// SHORT TERM
// 1a. Clean up code such as commented out lines that are useless, and rewrite functional comments
// 1. Have the textView change to give name and calculate age, along with the time away from birthday in hours (put all calculation methods in adapter class)
// 2. Animate progress bar to change over time (Calculate it, maybe use Youtube)
// 3. Change design of the cards on recycler view
// 4. Add notifications on switch and figure out how to provide notifications
// 5. Design trashcan for removing birthdays and link it with deleteOne method
// 6. Design UI for Saved Birthdays and Settings (think of characteristics for settings)

// LONG TERM
// 1. Implement user input functionality
// 2. Implement countdown function with a visual
// 3. Implement notification when countdown runs out
// 4. Fine-tune UI visuals and color scheme
// 5. Add comments to code and simplify where possible
// 6. Create icon for the app and also put the logo on the initial loading screen
// 7. Deploy on work phone and eventually to Github (also make READ.me)