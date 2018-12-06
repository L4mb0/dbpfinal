package com.example.root.dbpforomobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity
{
    String logged_as = "";
    Bundle extras;
    FlaskConnector request = new FlaskConnector(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    @Override
    protected void onResume()
    {
        super.onResume();

        extras = getIntent().getExtras();

        if (extras!= null)
            logged_as = extras.getString("logged_as");

    }



    public void onClickMyAccount(View v)
    {
        Intent myAccountActivityIntent = new Intent(MenuActivity.this,MyAccountActivity.class);
        myAccountActivityIntent.putExtra("logged_as",logged_as);
        startActivity(myAccountActivityIntent);
    }

    public void onClickLogOut(View v)
    {
        finish();
    }
}