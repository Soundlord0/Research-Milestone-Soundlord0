package com.example.researchmilestone;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import java.util.HashSet;
import java.util.Set;
import android.content.Intent;
import android.text.TextUtils;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.SwitchCompat;

public class ProfileSetupActivity extends AppCompatActivity {

    private EditText bioInput;
    private final Set<String> selectedInterests = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        bioInput = findViewById(R.id.bioInput);
        SwitchCompat appleWatchSwitch = findViewById(R.id.appleWatchSwitch);
        SwitchCompat ouraRingSwitch = findViewById(R.id.ouraRingSwitch);
        AppCompatButton marathon = findViewById(R.id.marathonButton);
        AppCompatButton casualRun = findViewById(R.id.casualRunButton);
        AppCompatButton triathlon = findViewById(R.id.triathlonButton);
        AppCompatButton sprint = findViewById(R.id.sprintButton);
        AppCompatButton trailRuns = findViewById(R.id.trailRunsButton);
        AppCompatButton intervals = findViewById(R.id.intervalsButton);
        styleGarnetSwitch(appleWatchSwitch);
        styleGarnetSwitch(ouraRingSwitch);
        setupInterestButton(marathon);
        setupInterestButton(casualRun);
        setupInterestButton(triathlon);
        setupInterestButton(sprint);
        setupInterestButton(trailRuns);
        setupInterestButton(intervals);


        ImageButton profilePhotoButton = findViewById(R.id.profilePhotoButton);
        profilePhotoButton.setOnClickListener(v ->
                Toast.makeText(this, "Photo upload comes next.", Toast.LENGTH_SHORT).show()

        );

        AppCompatButton completeSetupButton =
                findViewById(R.id.completeSetupButton);

        completeSetupButton.setOnClickListener(v -> {
            if (selectedInterests.isEmpty()) {
                Toast.makeText(
                        this,
                        "Select at least one interest.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            String bio = bioInput.getText().toString().trim();
            String interests = TextUtils.join(" • ", selectedInterests);

            Intent intent = new Intent(
                    ProfileSetupActivity.this,
                    AthleteProfileActivity.class
            );

            intent.putExtra("PROFILE_BIO", bio);
            intent.putExtra("PROFILE_INTERESTS", interests);
            intent.putExtra("APPLE_WATCH_CONNECTED", appleWatchSwitch.isChecked());
            intent.putExtra("OURA_RING_CONNECTED", ouraRingSwitch.isChecked());

            startActivity(intent);
            finish();

        });
    }
    private void styleGarnetSwitch(SwitchCompat toggle) {
        int garnet = ContextCompat.getColor(this, R.color.garnet);
        int gray = Color.parseColor("#C7CDD4");

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{}
        };

        int[] thumbColors = new int[]{
                Color.WHITE,
                Color.WHITE
        };

        int[] trackColors = new int[]{
                garnet,
                gray
        };

        toggle.setThumbTintList(
                new ColorStateList(states, thumbColors)
        );

        toggle.setTrackTintList(
                new ColorStateList(states, trackColors)
        );
    }
    private void setupInterestButton(AppCompatButton button) {
        button.setOnClickListener(v -> {
            String interest = button.getText().toString();

            if (selectedInterests.contains(interest)) {
                selectedInterests.remove(interest);
                button.setBackgroundResource(
                        R.drawable.bg_interest_unselected
                );
            } else {
                selectedInterests.add(interest);
                button.setBackgroundResource(
                        R.drawable.bg_interest_selected
                );
                button.setTextColor(ContextCompat.getColor(this, R.color.black));
            }
        });
    }
}