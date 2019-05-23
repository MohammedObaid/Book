package com.example.hala2.al_sharog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Ravi Tamada on 15/06/16.
 * www.androidhive.info
 */
public class MyApplication extends Application {


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
//        MultiDex.install(this);
    }

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Cocon_next_arabic_light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        handleSSLHandshake();


        // FORCE LTR
      //  mInstance = this;

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }



}
