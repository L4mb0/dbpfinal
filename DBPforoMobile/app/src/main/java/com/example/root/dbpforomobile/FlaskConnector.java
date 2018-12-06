package com.example.root.dbpforomobile;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FlaskConnector {
    String user = "";
    final String URL = ""; //AQUI CAMBIEN EL URL POR SU LOCAL HOST, CUANDO TERMINEN TODO METAN EL PY EN PYTHONANYWHERE Y PONGAN ESA URL
    String response = "";
    private static final String TAG = "RequestsFlask";
    public Context mContext;

    public FlaskConnector(Context context) {
        mContext = context;
    }

    public interface VolleyCallback {
        void onSuccess(String successResponse);

        void onFailure(String errorResponse);
    }

    public void login(
            final String username,
            final String password,
            final VolleyCallback callback) {
        String url = URL + "/do_login";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        callback.onSuccess(response);
                        FlaskConnector.this.response = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                        callback.onFailure(error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("User-Agent", "android");
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }


    public void register(
            final String username,
            final String password,
            final VolleyCallback callback) {
        String url = URL + "/do_signin";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                        callback.onFailure(error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("User-Agent", "android");
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };


        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }



    public void getUserId(
            final String username,
            final VolleyCallback callback) {
        String url = URL + "/id/" + username;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                        callback.onFailure(error.toString());
                    }
                }) {

        };
        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);

    }

    public void getUser(
            final String user_id,
            final VolleyCallback callback) {
        String url = URL + "/users/" + user_id;

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                        callback.onFailure(error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("User-Agent", "android");
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("logged_as", user_id);
                return params;
            }
        };
        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }


    public void changePassword(
            final String user_id,
            final String oldPassword,
            final String newPassword,
            final String confirmPassword,
            final VolleyCallback callback) {
        String url = URL + "/changePassword";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                        callback.onFailure(error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("User-Agent", "android");
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("logged_as", user_id);
                params.put("old_password", oldPassword);
                params.put("password", newPassword);
                params.put("confirm_password", confirmPassword);
                return params;
            }
        };
        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }


}