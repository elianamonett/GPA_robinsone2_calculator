package com.example.gpa_robinsone2_calculator;

// import all necessary classes
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// class declaration
public class MainActivity extends AppCompatActivity {

    // declaring various variables for course grades, the GPA calc button, and GPA result
    private final EditText[] gradeEditTexts = new EditText[5];
    private Button computeButton;
    private TextView gparesult;

    // keeps track of whether GPA has been calculated
    private boolean isGPACalculated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //displays activity_main.xml for user

        // initializing the UI
        gradeEditTexts[0] = findViewById(R.id.editTextCourse1);
        gradeEditTexts[1] = findViewById(R.id.editTextCourse2);
        gradeEditTexts[2] = findViewById(R.id.editTextCourse3);
        gradeEditTexts[3] = findViewById(R.id.editTextCourse4);
        gradeEditTexts[4] = findViewById(R.id.editTextCourse5);
        computeButton = findViewById(R.id.btn_compute);
        gparesult = findViewById(R.id.gpa_result);

        // this will define the behavior of the compute button when clicked
        computeButton.setOnClickListener(v -> {
            //is GPA calculated
            if (!isGPACalculated) {
                // calculating GPA and displays accordingly
                double totalGrade = 0;
                int validFields = 0;
                for (EditText editText : gradeEditTexts) {
                    if (!editText.getText().toString().isEmpty()) {
                        totalGrade += Double.parseDouble(editText.getText().toString());
                        validFields++;
                    }
                }

                if (validFields == 0) {
                    gparesult.setText(R.string.input_entergrade);
                    return;
                }

                double gpa = totalGrade / validFields;

                gparesult.setText(String.format("GPA: %.2f", gpa));

                // button color will change based on the gpa76
                computeButton.setText(R.string.change_clearform);
                if (gpa < 60) {
                    gparesult.setBackgroundColor(Color.RED);
                } else if (gpa >= 61 && gpa <= 79) {
                    gparesult.setBackgroundColor(Color.YELLOW);
                } else {
                    gparesult.setBackgroundColor(Color.GREEN);
                }
                // sets boolean flag
                isGPACalculated = true;
            } else {
                // clears the form
                for (EditText editText : gradeEditTexts) {
                    editText.setText("");
                }
                gparesult.setText("");
                computeButton.setText(R.string.compute_gpa);
                gparesult.setBackgroundColor(Color.TRANSPARENT);

                // resetting boolean flag
                isGPACalculated = false;
            }
        });
    }
}