package com.example.sai.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    public Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        save = (Button) findViewById(R.id.save_button);
        mDatabaseHelper = new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                EditText memoTitle = (EditText)findViewById(R.id.title);
                EditText memoContent = (EditText)findViewById(R.id.memo_content);
                String memoCont = memoContent.getText().toString();
                String memoTitl = memoTitle.getText().toString();
                if(memoTitl.length() != 0 )
                {
                    AddData(memoTitl, memoCont);
                    memoTitle.setText("");
                } else {
                    toastMessage("You must have to put something in the text field");
                }
                }
        }
        );
    }

    public void AddData(String newEntry, String newEntry2)
    {
        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry2);

        if (insertData)
        {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something has went wrong");
        }
    }
    //Toast message
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
