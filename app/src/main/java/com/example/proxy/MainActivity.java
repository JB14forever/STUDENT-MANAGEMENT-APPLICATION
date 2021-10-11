package com.example.proxy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaring id names
    TextView tvName, tvRollNo, tvBranch, tvPhone;
    EditText etName, etRollNo, etBranch, etPhone;
    Button btnInsert, btnUpdate, btnView, btnDelete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding the XML resources to make available for the Java File
        tvName = findViewById(R.id.tvName);
        tvRollNo = findViewById(R.id.tvRollNo);
        tvBranch = findViewById(R.id.tvBranch);
        tvPhone = findViewById(R.id.tvPhone);
        etName = findViewById(R.id.etName);
        etRollNo = findViewById(R.id.etRollNo);
        etBranch = findViewById(R.id.etBranch);
        etPhone = findViewById(R.id.etPhone);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btnDelete);
        DB = new DBHelper(this);

        // Insert Button -> Inserting New Records
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String StudRollNo = etRollNo.getText().toString();
                String StudName = etName.getText().toString();
                String StudBranch = etBranch.getText().toString();
                String StudPhone = etPhone.getText().toString();

                Boolean CheckInsertData = DB.InsertStudentData(StudRollNo, StudName, StudBranch, StudPhone);
                if (CheckInsertData == true){
                    Toast.makeText(MainActivity.this,
                            "New Entry Inserted",
                            Toast.LENGTH_LONG).show();
                    etRollNo.setText("");
                    etName.setText("");
                    etBranch.setText("");
                    etPhone.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "New Entry Not Inserted",
                            Toast.LENGTH_LONG).show();
                    etRollNo.setText("");
                    etName.setText("");
                    etBranch.setText("");
                    etPhone.setText("");
                }

            }
        });

        // Update Button -> Updating Records
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String StudRollNo = etRollNo.getText().toString();
                String StudName = etName.getText().toString();
                String StudBranch = etBranch.getText().toString();
                String StudPhone = etPhone.getText().toString();

                Boolean CheckUpdateData = DB.UpdateStudentData(StudRollNo, StudName, StudBranch, StudPhone);
                if (CheckUpdateData == true){
                    Toast.makeText(MainActivity.this,
                            "Entry Updated",
                            Toast.LENGTH_LONG).show();
                    etRollNo.setText("");
                    etName.setText("");
                    etBranch.setText("");
                    etPhone.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Entry Not Updated",
                            Toast.LENGTH_LONG).show();
                    etRollNo.setText("");
                    etName.setText("");
                    etBranch.setText("");
                    etPhone.setText("");
                }

            }
        });

        // Delete Button -> Deleting Records
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String StudRollNo = etRollNo.getText().toString();
                Boolean CheckDeleteData = DB.DeleteStudentData(StudRollNo);
                if (CheckDeleteData == true){
                    Toast.makeText(MainActivity.this,
                            "Entry Deleted",
                            Toast.LENGTH_LONG).show();
                    etRollNo.setText("");
                    etName.setText("");
                    etBranch.setText("");
                    etPhone.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Entry Not Deleted",
                            Toast.LENGTH_LONG).show();
                    etRollNo.setText("");
                    etName.setText("");
                    etBranch.setText("");
                    etPhone.setText("");
                }

            }
        });

        // View Button -> Display the Student Records
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.GetData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this,
                            "No Record Exists",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("RollNo = " + res.getString(0) + "\n" );
                    buffer.append("Name = " + res.getString(1) + "\n" );
                    buffer.append("Branch = " + res.getString(2) + "\n" );
                    buffer.append("Phone = " + res.getString(3) + "\n\n" );
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Records");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}