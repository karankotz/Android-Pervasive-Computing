package com.example.sai.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText name;

    public Button submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendFields(v);
                }
                }
        );
    }

    public void sendFields(View view) {
        EditText editext_name = findViewById(R.id.user_name);
        String name = editext_name.getText().toString();

        EditText editText_email = findViewById(R.id.user_email);
        String email = editText_email.getText().toString();

        EditText editText_phone = findViewById(R.id.user_num);
        String phone = editText_phone.getText().toString();

        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("NAME", name);
        intent.putExtra("EMAIL", email);
        intent.putExtra("PHONE", phone);
        startActivity(intent);
    }
}
