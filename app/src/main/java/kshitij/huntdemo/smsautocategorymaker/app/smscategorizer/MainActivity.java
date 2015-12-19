package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog progress = getProgressDialog("Initializing", "Figuring out stuffs ...");

        Category categoryObject = new Category();
        List l = categoryObject.categoryList();
        if (l.isEmpty()) {
            // executing for the first time. Populate the basic
            // categories and filter all past sms
            String[] catList = new String[]
                    {"Expenses", "Mobile Recharge and Internet", "Wallet", "Shopping", "Food", "Cab", "Investments", "OTP",  "Travel", "Services"};
            categoryObject.SaveList(catList);

            initiate();
        }

        progress.dismiss();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), CategoryList.class);
                startActivity(intent);
            }
        }, 1);
    }

    @NonNull
    private ProgressDialog getProgressDialog(String title, String Msg) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle(title);
        progress.setMessage(Msg);
        progress.show();
        return progress;
    }

    public void initiate() {
        Cursor smsCur = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        MessageIdentity mi = MessageIdentity.getInstance();

        try {
            if (smsCur.moveToFirst()) { // must check the result to prevent exception
                do {
                    // Data to be used thread_id, address, person, date, read, body, read, seen.
                    // Identify message
                    mi.Init(smsCur.getInt(smsCur.getColumnIndex("_id")),
                            smsCur.getString(smsCur.getColumnIndex("address")).toLowerCase(),
                            smsCur.getString(smsCur.getColumnIndex("body")));
                } while (smsCur.moveToNext());
            } else {
                System.out.println("No SMS Found");
            }
        } catch (Exception e) {
            System.out.println("Hello");
        }
    }
}
