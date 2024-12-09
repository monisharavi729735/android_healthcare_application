package com.example.healthcare;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class LabTestActivity extends AppCompatActivity {


    Button btn, btntocart;
    LinearLayout linearLayoutDoctors; // Declare LinearLayout to hold CardViews


    // Correctly define the packages array
    private String[][] packages = {
            {"Package 1: Full Body Checkup", "999"},
            {"Package 2: Blood Glucose Fasting", "299"},
            {"Package 3: COVID-19 Antibody IgG", "899"},
            {"Package 4: Thyroid Check", "499"},
            {"Package 5: Immunity Check", "699"}
    };


    private String[] package_details = {
            "Blood Glucose Fasting\n" +
                    "Complete Hemogram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",


            "Blood Glucose Fasting",


            "COVID-19 Antibody IgG",


            "Thyroid Profile - Total (T3, T4 & TSH Ultra-sensitive)",


            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total - 25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test); // Set content view first
        EdgeToEdge.enable(this); // Enable edge-to-edge after setting content view


        // Initialize the button
        btn = findViewById(R.id.buttonLabBack);
        btntocart = findViewById(R.id.buttonLabGoToCart);
        linearLayoutDoctors = findViewById(R.id.linearLayoutDoctors); // Initialize the LinearLayout


        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Set a click listener for the back button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
                finish();
            }
        });


        btntocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
                finish();
            }
        });


        // Add CardViews for each package
        addPackageCards();
    }


    private void addPackageCards() {
        LayoutInflater inflater = LayoutInflater.from(this);


        for (int i = 0; i < packages.length; i++) {
            final int index = i;
            View cardView = inflater.inflate(R.layout.card_package, linearLayoutDoctors, false);
            TextView packageName = cardView.findViewById(R.id.package_name);
            TextView packagePrice = cardView.findViewById(R.id.package_price);


            packageName.setText(packages[i][0]); // Set package name
            packagePrice.setText("Price: ₹" + packages[i][1]); // Set package price


            // Set an OnClickListener for the card
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                    intent.putExtra("text1", packages[index][0]); // Package name
                    intent.putExtra("text2", package_details[index]); // Package details
                    intent.putExtra("text3", packages[index][1]); // Package price
                    startActivity(intent);
                }
            });


            // Add the CardView to the LinearLayout
            linearLayoutDoctors.addView(cardView);
        }
    }
}

