package com.example.myportfolio;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        // Project 1: Weather-Adaptive Vehicle Detection
        TextView tvProj1Title = findViewById(R.id.tvProj1Title);
        TextView tvProj1Period = findViewById(R.id.tvProj1Period);
        TextView tvProj1Tech = findViewById(R.id.tvProj1Tech);
        TextView tvProj1Desc = findViewById(R.id.tvProj1Desc);

        tvProj1Title.setText("Weather-Adaptive Vehicle Detection via Fog Synthesis and Rain Rendering");
        tvProj1Period.setText("Aug 2024 - Feb 2025");
        tvProj1Tech.setText("Published at ACDSA 2026 (IEEE) | Python, PyTorch, OpenCV, Monodepth2, YOLOv12/v13, RT-DETR");
        tvProj1Desc.setText("• Proposed a data augmentation pipeline simulating fog and rain conditions for Vietnam's climate using depth-based synthesis and physics-based rendering.\n\n" +
                "• Benchmarked SOTA models on a self-collected 1,180-image private test set; improved RT-DETR mAP@0.5 from 0.2192 to 0.4297 (+96% rel) with randomized augmentation.");

        // Project 2: Vietnamese Food QA
        TextView tvProj2Title = findViewById(R.id.tvProj2Title);
        TextView tvProj2Period = findViewById(R.id.tvProj2Period);
        TextView tvProj2Tech = findViewById(R.id.tvProj2Tech);
        TextView tvProj2Desc = findViewById(R.id.tvProj2Desc);

        tvProj2Title.setText("Vietnamese Food Question Answering Benchmark");
        tvProj2Period.setText("Ongoing");
        tvProj2Tech.setText("NLP Research | Python, LLMs, VLMs, Knowledge Graphs, Hugging Face");
        tvProj2Desc.setText("• Designing a unified Food QA benchmark combining health-aware personalization, multilingual/multicultural context, KG reasoning, and visual understanding for Vietnamese cuisine.\n\n" +
                "• Developing multi-hop QA with CoT reasoning and hallucination detection.");

        // Project 3: B2B Marketplace
        TextView tvProj3Title = findViewById(R.id.tvProj3Title);
        TextView tvProj3Period = findViewById(R.id.tvProj3Period);
        TextView tvProj3Tech = findViewById(R.id.tvProj3Tech);
        TextView tvProj3Desc = findViewById(R.id.tvProj3Desc);

        tvProj3Title.setText("B2B Marketplace Platform with AI-Powered Tools");
        tvProj3Period.setText("2024 - 2025");
        tvProj3Tech.setText("Team Lead | React, TypeScript, FastAPI, PostgreSQL, TailwindCSS, Zustand, JWT");
        tvProj3Desc.setText("• Built a full-stack B2B platform connecting suppliers and retailers with RFQ workflows, contract management, order tracking, and real-time messaging.\n\n" +
                "• Integrated AI assistant tools for price intelligence, review summarization, supplier evaluation, and negotiation strategy. Deployed on Vercel/Render with role-based access control.");
    }
}
