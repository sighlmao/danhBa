package com.example.danhba;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewContacts;
    private FloatingActionButton fabAddContact;
    private Button btnManageEmployees, btnManageDepartments;
    private EditText editTextSearch;

    private ArrayList<Contact> contactList;
    private ContactDataSource contactDataSource;

    private static final int ADD_CONTACT_REQUEST = 1;
    private static final int ADD_EMPLOYEE_REQUEST = 2;
    private static final int ADD_DEPARTMENT_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        fabAddContact = findViewById(R.id.fabAddContact);
        btnManageEmployees = findViewById(R.id.btnManageEmployees);
        btnManageDepartments = findViewById(R.id.btnManageDepartments);
        editTextSearch = findViewById(R.id.editTextSearch);

        // Khởi tạo danh sách dữ liệu mẫu
        contactList = new ArrayList<>();
        contactDataSource = new ContactDataSource(this);
        contactDataSource.open();
        contactList.addAll(contactDataSource.getAllContacts());

        // Sắp xếp danh sách theo bảng chữ cái
        Collections.sort(contactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getName().compareTo(contact2.getName());
            }
        });

        // Khởi tạo RecyclerView và thiết lập adapter
        ContactAdapter adapter = new ContactAdapter(contactList);
        recyclerViewContacts.setAdapter(adapter);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        // Bắt sự kiện khi nhấn nút Thêm danh bạ
        fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở activity để thêm contact mới
                Intent intent = new Intent(MainActivity.this, addEmployeeActivity.class);
                startActivityForResult(intent, ADD_CONTACT_REQUEST);
            }
        });

        // Bắt sự kiện khi nhấn nút Quản lý Thông tin nhân viên
        btnManageEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở activity để thêm nhân viên mới
                Intent intent = new Intent(MainActivity.this, addEmployeeActivity.class);
                startActivityForResult(intent, ADD_EMPLOYEE_REQUEST);
            }
        });

        // Bắt sự kiện khi nhấn nút Quản lý Thông tin đơn vị
        btnManageDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở activity để thêm phòng ban mới
                Intent intent = new Intent(MainActivity.this, addDepartmentActivity.class);
                startActivityForResult(intent, ADD_DEPARTMENT_REQUEST);
            }
        });

        // Bắt sự kiện tìm kiếm
        editTextSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Thực hiện tìm kiếm
                    String keyword = editTextSearch.getText().toString().trim();
                    searchContacts(keyword);
                    return true;
                }
                return false;
            }
        });
    }

    // Hàm tìm kiếm contact theo từ khóa
    private void searchContacts(String keyword) {
        // Thực hiện tìm kiếm và cập nhật danh sách hiển thị trên RecyclerView
        List<Contact> filteredList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(contact);
            }
        }
        // Cập nhật RecyclerView
        ContactAdapter adapter = new ContactAdapter(filteredList);
        recyclerViewContacts.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_CONTACT_REQUEST:
                    if (data != null) {
                        Contact newContact = (Contact) data.getSerializableExtra("newContact");
                        if (newContact != null) {
                            // Thêm contact mới vào danh sách và cập nhật RecyclerView
                            contactList.add(newContact);
                            Collections.sort(contactList, new Comparator<Contact>() {
                                @Override
                                public int compare(Contact contact1, Contact contact2) {
                                    return contact1.getName().compareTo(contact2.getName());
                                }
                            });
                            recyclerViewContacts.getAdapter().notifyDataSetChanged();

                            // Lưu contact mới vào cơ sở dữ liệu
                            contactDataSource.addContact(newContact);
                        }
                    }
                    break; // Thêm dấu chấm phẩy và break ở đây
                case ADD_EMPLOYEE_REQUEST:
                    // Xử lý kết quả từ activity thêm nhân viên
                    break;
                case ADD_DEPARTMENT_REQUEST:
                    // Xử lý kết quả từ activity thêm phòng ban
                    break;
            }
        }
    }
}

