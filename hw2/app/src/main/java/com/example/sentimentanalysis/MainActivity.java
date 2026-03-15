package com.example.sentimentanalysis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnSubmit;
    private TextView tvEmoji, tvResult;
    private View rootLayout;

    // TODO: Replace with your actual Gemini API key
    private static final String API_KEY = "YOUR_GEMINI_API_KEY_HERE";
    private static final String API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);
        etInput = findViewById(R.id.etInput);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvEmoji = findViewById(R.id.tvEmoji);
        tvResult = findViewById(R.id.tvResult);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = etInput.getText().toString().trim();
                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a sentence", Toast.LENGTH_SHORT).show();
                    return;
                }
                analyzeSentiment(inputText);
            }
        });
    }

    private void analyzeSentiment(String text) {
        btnSubmit.setEnabled(false);
        btnSubmit.setText("Analyzing...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Build the request JSON
                    JSONObject requestBody = new JSONObject();
                    JSONArray contents = new JSONArray();
                    JSONObject content = new JSONObject();
                    JSONArray parts = new JSONArray();
                    JSONObject part = new JSONObject();

                    String prompt = "Analyze the sentiment of the following text and respond with ONLY one word: " +
                            "'positive', 'negative', or 'neutral'. Text: \"" + text + "\"";

                    part.put("text", prompt);
                    parts.put(part);
                    content.put("parts", parts);
                    contents.put(content);
                    requestBody.put("contents", contents);

                    // Make HTTP request
                    URL url = new URL(API_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    os.write(requestBody.toString().getBytes("UTF-8"));
                    os.close();

                    int responseCode = conn.getResponseCode();
                    BufferedReader reader;

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    } else {
                        reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    }

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse response
                    String sentiment = "neutral";
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        JSONObject jsonResponse = new JSONObject(response.toString());
                        JSONArray candidates = jsonResponse.getJSONArray("candidates");
                        JSONObject firstCandidate = candidates.getJSONObject(0);
                        JSONObject contentObj = firstCandidate.getJSONObject("content");
                        JSONArray partsArr = contentObj.getJSONArray("parts");
                        String resultText = partsArr.getJSONObject(0).getString("text").trim().toLowerCase();

                        if (resultText.contains("positive")) {
                            sentiment = "positive";
                        } else if (resultText.contains("negative")) {
                            sentiment = "negative";
                        } else {
                            sentiment = "neutral";
                        }
                    } else {
                        // If API fails, do simple keyword-based fallback
                        sentiment = simpleSentiment(text);
                    }

                    final String finalSentiment = sentiment;

                    // Update UI on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUI(finalSentiment);
                            btnSubmit.setEnabled(true);
                            btnSubmit.setText("Submit");
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    // Fallback to simple analysis
                    final String fallback = simpleSentiment(text);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUI(fallback);
                            btnSubmit.setEnabled(true);
                            btnSubmit.setText("Submit");
                            Toast.makeText(MainActivity.this,
                                    "API unavailable, used offline analysis", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * Simple keyword-based sentiment analysis as fallback
     */
    private String simpleSentiment(String text) {
        text = text.toLowerCase();

        String[] positiveWords = {"good", "great", "love", "happy", "excellent", "amazing",
                "wonderful", "fantastic", "awesome", "nice", "beautiful", "enjoy",
                "like", "best", "fun", "glad", "thank", "thanks", "yes", "cool",
                "perfect", "brilliant"};

        String[] negativeWords = {"bad", "hate", "terrible", "awful", "horrible", "ugly",
                "worst", "poor", "sad", "angry", "annoying", "boring",
                "don't like", "no", "never", "wrong", "fail", "sick",
                "disappointed", "unfortunately", "sorry"};

        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : positiveWords) {
            if (text.contains(word)) positiveCount++;
        }
        for (String word : negativeWords) {
            if (text.contains(word)) negativeCount++;
        }

        if (positiveCount > negativeCount) return "positive";
        if (negativeCount > positiveCount) return "negative";
        return "neutral";
    }

    private void updateUI(String sentiment) {
        switch (sentiment) {
            case "positive":
                rootLayout.setBackgroundColor(Color.parseColor("#2ECC71")); // Green
                tvEmoji.setText("\uD83D\uDE03"); // 😃
                tvResult.setText("Positive!");
                tvResult.setTextColor(Color.WHITE);
                break;
            case "negative":
                rootLayout.setBackgroundColor(Color.parseColor("#8B1A1A")); // Dark red
                tvEmoji.setText("\uD83D\uDE1E"); // 😞
                tvResult.setText("Negative!");
                tvResult.setTextColor(Color.WHITE);
                break;
            default:
                rootLayout.setBackgroundColor(Color.parseColor("#F39C12")); // Orange/yellow
                tvEmoji.setText("\uD83D\uDE10"); // 😐
                tvResult.setText("Neutral");
                tvResult.setTextColor(Color.WHITE);
                break;
        }
    }
}
