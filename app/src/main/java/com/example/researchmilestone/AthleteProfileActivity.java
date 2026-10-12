package com.example.researchmilestone;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AthleteProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_profile);
        TextView userNameText = findViewById(R.id.userNameText);
        TextView userBioText = findViewById(R.id.userBioText);
        TextView userInterestsText = findViewById(R.id.userInterestsText);

        String bio = getIntent().getStringExtra("PROFILE_BIO");
        String interests = getIntent().getStringExtra("PROFILE_INTERESTS");

        userNameText.setText("Campus Athlete");

        if (bio == null || bio.isEmpty()) {
            userBioText.setText("No runner bio added yet.");
        } else {
            userBioText.setText(bio);
        }

        if (interests == null || interests.isEmpty()) {
            userInterestsText.setText("No interests selected.");
        } else {
            userInterestsText.setText(interests);
        }
        AppCompatImageButton editButton = findViewById(R.id.editProfileButton);

        editButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Edit Profile")
                    .setMessage(
                            "This will let the athlete update their bio, " +
                                    "interests, and hardware connections."
                    )
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Save", null)
                    .show();
        });
    }
}