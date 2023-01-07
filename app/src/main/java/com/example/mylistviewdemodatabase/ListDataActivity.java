package com.example.mylistviewdemodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        listView = findViewById(R.id.listViewId);
        databaseHelper = new DatabaseHelper(this);
        loadData();
    }

    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseHelper.showAllData();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No data is available in database ", Toast.LENGTH_SHORT).show();
        }else  {
            while (cursor.moveToNext()){listData.add(cursor.getString(0)+"\t "+ cursor.getString(1));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,
                R.id.textViewId,listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String selectValue = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(ListDataActivity.this, "Selected value :"+selectValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}