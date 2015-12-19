package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategorySmsList extends AppCompatActivity {
    String[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sms_list);
        Integer catId = 0;

        Bundle data = getIntent().getExtras();
        try {
            catId = data.getInt("catId");
        } catch (Exception e) {
            new AlertDialog.Builder(this).setTitle("EMPTY_CATEGORY_PASSED_ERROR")
                    .setMessage("Category not passed. Cannot Display SMS")
                    .setNegativeButton(
                            android.R.string.no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(1);
                                }
                            }
                    )
                    .show();
        }

        CategorySMSMapping csm = new CategorySMSMapping();
        Category cat = Category.load(Category.class, catId);
        TextView t = (TextView)findViewById(R.id.textView);
        t.setText(cat.name);

        List l = csm.fromCategory(cat);
        ArrayList<CategorySMSMapping> catList = new ArrayList<CategorySMSMapping>(l);
        CategorySmsListAdapter csla = new CategorySmsListAdapter(this, catList);
        csla.setContext(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(csla);
    }
}
