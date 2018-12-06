package com.example.root.dbpforomobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity
{
    String logged_as = "";
    Bundle extras;
    FlaskConnector request = new FlaskConnector(this);

    EditText oldPassword, newPassword, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        extras = getIntent().getExtras();

        if (extras!= null)
            logged_as = extras.getString("logged_as");

        oldPassword = (EditText) findViewById(R.id.oldPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
    }

    public void onClickChangePassword(View v)
    {

        request.changePassword(
                logged_as,
                oldPassword.getText().toString(),
                newPassword.getText().toString(),
                confirmPassword.getText().toString(),
                new FlaskConnector.VolleyCallback() {
                    @Override
                    public void onSuccess(String successResponse) {
                        Toast.makeText(ChangePasswordActivity.this,successResponse,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String errorResponse) {
                        Toast.makeText(ChangePasswordActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}