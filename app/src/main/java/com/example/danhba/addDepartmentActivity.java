package com.example.danhba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addDepartmentActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department_contact);

        editTextName = findViewById(R.id.editTextEmployeeFullName); // Sửa ID ở đây
        buttonSave = findViewById(R.id.buttonAddEmployee); // Sửa ID ở đây

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDepartment();
            }
        });
    }

    private void saveDepartment() {
        String name = editTextName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Name is required!");
            return;
        }

        // Tạo đối tượng Department mới và truyền lại kết quả cho MainActivity
        Department newDepartment = new Department();
        newDepartment.setName(name);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("newDepartment", (CharSequence) newDepartment);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
