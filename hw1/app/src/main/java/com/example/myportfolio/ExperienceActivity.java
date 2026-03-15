package com.example.myportfolio;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        // Education section
        TextView tvEduSchool = findViewById(R.id.tvEduSchool);
        TextView tvEduPeriod = findViewById(R.id.tvEduPeriod);
        TextView tvEduMajor = findViewById(R.id.tvEduMajor);
        TextView tvEduDescription = findViewById(R.id.tvEduDescription);

        tvEduSchool.setText("University of Information Technology, VNU-HCM");
        tvEduPeriod.setText("Mar 2022 - May 2026");
        tvEduMajor.setText("Bachelor of Information Systems");
        tvEduDescription.setText("GPA: 8.6/10 - Advanced Program (English-based curriculum)\nIELTS: 6.0");

        // Work Experience section
        TextView tvWorkTitle = findViewById(R.id.tvWorkTitle);
        TextView tvWorkPlace = findViewById(R.id.tvWorkPlace);
        TextView tvWorkPeriod = findViewById(R.id.tvWorkPeriod);
        TextView tvWorkDescription = findViewById(R.id.tvWorkDescription);

        tvWorkTitle.setText("Research Assistant");
        tvWorkPlace.setText("University of Information Technology (UIT)");
        tvWorkPeriod.setText("Aug 2024 - Jan 2026");
        tvWorkDescription.setText("Assisted research projects related to stock market analysis, computer vision, and healthcare AI.");
    }
}
