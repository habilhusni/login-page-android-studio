package com.example.husnihabil.simplelogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by husnihabil on 07/06/17.
 */

public class SecondActivity extends AppCompatActivity {
    TextView descriptions, nameHeader, nameBody, joinDate;
    ProgressBar mLoadingIndicator;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        descriptions = (TextView) findViewById(R.id.description_body);
        nameHeader = (TextView) findViewById(R.id.username_login);
        nameBody = (TextView) findViewById(R.id.username);
        joinDate = (TextView) findViewById(R.id.join_date);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        avatar = (ImageView) findViewById(R.id.photo_holder);

        Intent in = getIntent();
        String userInput = in.getStringExtra("userInput");
        String userPassword = in.getStringExtra("userPassword");

        String url = "https://kudo-practical.herokuapp.com/authentication/login";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_mail", userInput);
        params.put("user_password", userPassword);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody)          {
                if (responseBody == null) { descriptions.setText("Response Body in onSuccess is NULL!!"); }
                //success response, do something with it!
                String response2 = new String(responseBody);
                //response.setText(response2);

                try {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(response2);

                    String finalJSON = buffer.toString();
                    JSONObject parentObject = new JSONObject(finalJSON);
                    JSONObject objectMessage = parentObject.getJSONObject("message");
                    String description = objectMessage.getString("description");
                    String firstName = objectMessage.getString("first_name");
                    String lastName = objectMessage.getString("last_name");
                    String dateJoin = objectMessage.getString("join_date");
                    String avatars = objectMessage.getString("avatar");

                    nameHeader.setText(firstName+" "+lastName);
                    nameBody.setText(firstName+" "+lastName);
                    joinDate.setText(dateJoin);
                    new DownloadImageTask((ImageView) findViewById(R.id.photo_holder))
                            .execute(avatars);
                    descriptions.setText(description);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[]                        responseBody, Throwable error) {
                if (responseBody == null) { descriptions.setText("Response Body in onFailure is NULL!!"); }
                //error response, do something with it!
                String response2 = new String(responseBody);
                descriptions.setText(response2);
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            avatar.setVisibility(View.INVISIBLE);
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            avatar.setVisibility(View.VISIBLE);
        }
    }

}
