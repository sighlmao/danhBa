package com.example.danhba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addEmployeeActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department_contact);

        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });
    }

    private void saveEmployee() {
        String name = editTextName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Name is required!");
            return;
        }

        // Tạo đối tượng Employee mới và truyền lại kết quả cho MainActivity
        Employee newEmployee = new Employee();
        newEmployee.setName(name);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("newEmployee", newEmployee);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
