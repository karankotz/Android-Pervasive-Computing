package com.example.sai.assignment1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    public Button add_memo;
    private ListView mListView;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty2);

        Intent intent = getIntent();

        String name = intent.getStringExtra("NAME");
        String phone = intent.getStringExtra("PHONE");
        String email = intent.getStringExtra("EMAIL");

        TextView textView_name = findViewById(R.id.name_activit2);
        textView_name.setText(name);

        TextView textView_email = findViewById(R.id.email_activity2);
        textView_email.setText(email);

        TextView textView_phone = findViewById(R.id.phone_activity2);
        textView_phone.setText(phone);

        mListView = (ListView) findViewById(R.id.list_view);
        mDatabaseHelper = new DatabaseHelper(this);
        populateListView();

        add_memo = (Button) findViewById(R.id.addmemo_button);
        add_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendFields(v);
                }
        }
        );

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "This is the list Item", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void sendFields(View view) {
        Intent intent = new Intent(this, MemoActivity.class);
        startActivity(intent);
    }

    private void populateListView() {
        //get the data and append that to the list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> listContent = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in the column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
            listContent.add(data.getString(2));
        }
        //ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        //mListView.setAdapter(adapter);

        MemoAdapter adapt = new MemoAdapter(listData, listContent, this);
        //ListView lView = (ListView)findViewById(R.id.list_view);
        mListView.setAdapter(adapt);


    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        populateListView();
    }

}
