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

import com.example.root.dbpforomobile.entities.User;

public class PostActivity extends AppCompatActivity{

    FlaskConnector request = new FlaskConnector(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("Post!");
    }

    public void onClickCreateUser(View v){
        EditText txtTitle   = (EditText)findViewById(R.id.postTitle);
        EditText txtContent   = (EditText)findViewById(R.id.postText);

        final String title = txtTitle.getText().toString();
        final String content = txtContent.getText().toString();


        request.post(
                title,
                content,
                new FlaskConnector.VolleyCallback() {
                    @Override
                    public void onSuccess(String successResponse)
                    {
                        if (successResponse.equals("post registered"))
                        {
                            Toast.makeText(PostActivity.this,"post Created",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(PostActivity.this,successResponse,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorResponse)
                    {
                        Toast.makeText(PostActivity.this, "Unable to connect to server", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
