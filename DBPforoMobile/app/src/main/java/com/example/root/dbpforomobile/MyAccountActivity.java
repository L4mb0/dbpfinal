package com.example.root.dbpforomobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyAccountActivity extends AppCompatActivity {
    String logged_as = "";
    Bundle extras;
    FlaskConnector request = new FlaskConnector(this);

    LinearLayout mainLayout;
    TextView accountUsername, accountEmail, accountPosition, accountJoined, accountAge, accountVotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        extras = getIntent().getExtras();

        if (extras!= null)
            logged_as = extras.getString("logged_as");

        setTitle("My Account");
        mainLayout = (LinearLayout) findViewById(R.id.accountLayout);
        accountUsername = (TextView) findViewById(R.id.accountUsername);
        update();
    }

    public void onClickChangePassword(View v)
    {
        Intent ChangePasswordIntent = new Intent(MyAccountActivity.this,ChangePasswordActivity.class);
        ChangePasswordIntent.putExtra("logged_as",logged_as);
        startActivity(ChangePasswordIntent);
    }

    private void update()
    {
        request.getUser(
                logged_as,
                new FlaskConnector.VolleyCallback() {
                    @Override
                    public void onSuccess(String response) {
                        JSONArray jsonArray;
                        JSONObject jsonObject;

                        try {
                            jsonArray = new JSONArray(response);
                            jsonObject = jsonArray.getJSONObject(0);

                            String AccountUsername = jsonObject.getString("username");


                            accountUsername.setText(AccountUsername);




                        }
                        catch (JSONException e){
                            Toast.makeText(MyAccountActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorResponse) {
                        Toast.makeText(MyAccountActivity.this,"Unable to connect to server",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    private void createText()
    {
        mainLayout.setVisibility(View.VISIBLE);
    }

}