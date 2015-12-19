package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryList extends AppCompatActivity {
    String[] arr;
    Category categoryObject = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        List l = categoryObject.categoryList();

        ArrayList<Category> catList = new ArrayList<Category>(l);
        CategoryListAdapter cla = new CategoryListAdapter(this, catList);
        cla.setContext(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(cla);
    }
}
