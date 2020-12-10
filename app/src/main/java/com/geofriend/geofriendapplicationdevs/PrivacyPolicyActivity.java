package com.geofriend.geofriendapplicationdevs;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PrivacyPolicyActivity extends Activity {

    TextView privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);


        privacy_policy = (TextView)findViewById(R.id.termsOfUse2);

        InputStream inputStream = getResources().openRawResource(R.raw.geofriend_privacy_policy);

        privacy_policy.setMovementMethod(new ScrollingMovementMethod());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        privacy_policy.setText(byteArrayOutputStream.toString());

    }

}