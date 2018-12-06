package com.example.root.dbpforomobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.dbpforomobile.entities.User;

public class MainActivity extends AppCompatActivity {

    FlaskConnector request = new FlaskConnector(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Bienvenido al Batiforo :D");
    }


    public void onClickBtnLogin(View v)
    {
        EditText txtUsername   = (EditText)findViewById(R.id.txtUsername);
        EditText txtPassword   = (EditText)findViewById(R.id.txtPassword);
        User user = new User();
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        final String username = user.getUsername();
        //Toast.makeText(this, new Gson().toJson(user), Toast.LENGTH_LONG).show();
        String response="";
        request.login(
                user.getUsername(),
                user.getPassword(),
                new FlaskConnector.VolleyCallback()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        if (response.equals("Success"))
                        {

                            request.getUserId(
                                    username,
                                    new FlaskConnector.VolleyCallback() {
                                        @Override
                                        public void onSuccess(String successResponse) {
                                            request.user = successResponse;
                                            Intent MenuActivityIntent = new Intent(MainActivity.this, MenuActivity.class);
                                            MenuActivityIntent.putExtra("logged_as",request.user);
                                            startActivity(MenuActivityIntent);
                                        }

                                        @Override
                                        public void onFailure(String errorResponse) {
                                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                        }
                                    }
                            );

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Incorrect username or password ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(String error)
                    {
                        Toast.makeText(MainActivity.this, "Unable to connect to server", Toast.LENGTH_LONG).show();
                    }
                });

    }


    public void onClickRegister(View v){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

}