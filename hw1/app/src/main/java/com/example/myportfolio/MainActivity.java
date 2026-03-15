package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set profile data
        TextView tvName = findViewById(R.id.tvName);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvLocation = findViewById(R.id.tvLocation);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvGithub = findViewById(R.id.tvGithub);
        TextView tvAboutMe = findViewById(R.id.tvAboutMe);

        tvName.setText("Minh Bao Hoang");
        tvTitle.setText("AI Engineer");
        tvLocation.setText("Thu Duc, Ho Chi Minh City, Vietnam");
        tvEmail.setText("hoangbaominh22112002@gmail.com");
        tvPhone.setText("0358641817");
        tvGithub.setText("github.com/baominh2211");
        tvAboutMe.setText("AI Engineer with research experience in computer vision and NLP, published at ACDSA 2026 (IEEE). Skilled in building end-to-end ML pipelines from data augmentation to model deployment. Experienced in leading full-stack web development with integrated AI tools. Seeking opportunities to apply deep learning to real-world challenges.");

        // Skills
        TextView tvSkills = findViewById(R.id.tvSkills);
        tvSkills.setText("Computer Vision: Object Detection, Multi-Object Tracking\n" +
                "NLP: Food QA, LLMs/VLMs, Knowledge Graphs\n" +
                "Time-Series: Forecasting\n" +
                "Frameworks: PyTorch, OpenCV, Hugging Face Transformers\n" +
                "Vision Tools: Monodepth2, YOLOv12/v13, RT-DETR\n" +
                "Backend/Deploy: FastAPI, Docker, Git, PostgreSQL\n" +
                "Frontend: React, TypeScript\n" +
                "Programming: Python, JavaScript/TypeScript, Java, SQL");

        // Navigate to Experience page
        Button btnExperience = findViewById(R.id.btnExperience);
        btnExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExperienceActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to Projects page
        Button btnProjects = findViewById(R.id.btnProjects);
        btnProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProjectsActivity.class);
                startActivity(intent);
            }
        });

        // Open GitHub (Implicit Intent)
        tvGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://github.com/baominh2211");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        // Open Email (Implicit Intent)
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:hoangbaominh22112002@gmail.com"));
                startActivity(emailIntent);
            }
        });

        // Open Phone Dialer (Implicit Intent)
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:0358641817");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
    }
}
