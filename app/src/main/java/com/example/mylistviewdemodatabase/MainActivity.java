package com.example.mylistviewdemodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper databaseHelper;

    private EditText idEditText, nameEditText;
    private Button saveButton,showButton,updateButton,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        idEditText = findViewById(R.id.idEditText);
        nameEditText = findViewById(R.id.nameEditText);

        saveButton = findViewById(R.id.saveButtonId);
        showButton = findViewById(R.id.showButtonId);
        updateButton = findViewById(R.id.updateButtonId);
        deleteButton = findViewById(R.id.deleteButtonId);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String id = idEditText.getText().toString();
        String name = nameEditText.getText().toString();

        if (v.getId() == R.id.saveButtonId) {

            if (id.equals("") && name.equals("")) {
                Toast.makeText(this, "Please enter all the data", Toast.LENGTH_SHORT).show();
            }else{
               long rowNumber = databaseHelper.saveData(id,name);
                if (rowNumber > -1) {
                    Toast.makeText(this, "Data is inserted successfully", Toast.LENGTH_SHORT).show();
                    idEditText.setText("");
                    nameEditText.setText("");
                }else {
                    Toast.makeText(this, "Data is not inserted successfully", Toast.LENGTH_SHORT).show();
                }

            }

        }else if (v.getId() == R.id.showButtonId) {
            Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.updateButtonId) {

        }else if (v.getId() == R.id.deleteButtonId) {

        }

    }
}