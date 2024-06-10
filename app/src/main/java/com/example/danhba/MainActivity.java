import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewContacts;
    private FloatingActionButton fabAddContact;
    private Button btnManageEmployees, btnManageDepartments;
    private EditText editTextSearch;
    private TextView textViewTitle;

    private ArrayList<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        fabAddContact = findViewById(R.id.fabAddContact);
        btnManageEmployees = findViewById(R.id.btnManageEmployees);
        btnManageDepartments = findViewById(R.id.btnManageDepartments);
        editTextSearch = findViewById(R.id.editTextSearch);
        textViewTitle = findViewById(R.id.textViewTitle);

        // Khởi tạo danh sách dữ liệu mẫu
        initializeData();

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
                // Xử lý sự kiện Thêm danh bạ ở đây
            }
        });

        // Bắt sự kiện khi nhấn nút Quản lý Thông tin nhân viên
        btnManageEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện Quản lý Thông tin nhân viên ở đây
            }
        });

        // Bắt sự kiện khi nhấn nút Quản lý Thông tin đơn vị
        btnManageDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện Quản lý Thông tin đơn vị ở đây
            }
        });
    }

    // Khởi tạo dữ liệu mẫu
    private void initializeData() {
        contactList = new ArrayList<>();
        // Thêm các contact vào danh sách ở đây
    }
}
