package com.example.root.dbpforomobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.dbpforomobile.entities.User;

public class RegisterActivity extends AppCompatActivity {

    FlaskConnector request = new FlaskConnector(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
    }

    public void onClickCreateUser(View v){
        EditText txtUsername   = (EditText)findViewById(R.id.registerUsername);
        EditText txtPassword   = (EditText)findViewById(R.id.registerPassword);

        final String username = txtUsername.getText().toString();
        final String password = txtPassword.getText().toString();


        request.register(
                username,
                password,
                new FlaskConnector.VolleyCallback() {
                    @Override
                    public void onSuccess(String successResponse)
                    {
                        if (successResponse.equals("user registered"))
                        {
                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,successResponse,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorResponse)
                    {
                        Toast.makeText(RegisterActivity.this, "Unable to connect to server", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}