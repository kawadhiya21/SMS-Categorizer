package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SZLAP299 on 12/18/15.
 */
public class CategorySmsListAdapter extends ArrayAdapter {
    Context c;
    public CategorySmsListAdapter(Context context, ArrayList<CategorySMSMapping> categories) {
        super(context, 0, categories);
    }

    public void setContext(Context c) {
        this.c = c;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CategorySMSMapping csm = (CategorySMSMapping) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sms_category_list_item, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.sms_list_item);
        tv.setText(csm.sms_msg);
        return convertView;
    }
}
