package com.example.root.dbpforomobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    TextView postTitle;
    TextView postContent;
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
        postTitle = (TextView) findViewById(R.id.postTitle);
        postContent = (TextView) findViewById(R.id.postText);

        setTitle("Menu");
        update();

        if (extras!= null)
            logged_as = extras.getString("logged_as");

    }



    public void onClickMyAccount(View v)
    {
        Intent myAccountActivityIntent = new Intent(MenuActivity.this,MyAccountActivity.class);
        myAccountActivityIntent.putExtra("logged_as",logged_as);
        startActivity(myAccountActivityIntent);
    }

    private void update()
    {
        EditText txtTitle   = (EditText)findViewById(R.id.postTitle);
        EditText txtContent   = (EditText)findViewById(R.id.postText);

        final String title = txtTitle.getText().toString();
        final String content = txtContent.getText().toString();

        request.getPost(
                title, 
                content,
                new FlaskConnector.VolleyCallback() {
                    @Override
                    public void onSuccess(String response) {
                        JSONArray jsonArray;
                        JSONObject jsonObject;

                        try {
                            jsonArray = new JSONArray(response);
                            jsonObject = jsonArray.getJSONObject(0);

                            String PostTitle = jsonObject.getString("title: ");

                            postTitle.setText(PostTitle);


                            String PostContent = jsonObject.getString("content: ");

                            postContent.setText(PostContent);




                        }
                        catch (JSONException e){
                            Toast.makeText(MenuActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorResponse) {
                        Toast.makeText(MenuActivity.this,"Unable to connect to server",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void onClickLogOut(View v)
    {
        finish();
    }


}