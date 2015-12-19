package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by SZLAP299 on 11/26/15.
 */
@Table(name = "category_sms_mapping")
public class CategorySMSMapping extends Model {
    @Column(name = "category_id")
    public Category cat;

    @Column(name = "sms_id", unique = true)
    public Integer sms_id;

    @Column(name = "sms_sender")
    public String sms_sender;

    @Column(name = "sms_msg")
    public String sms_msg;

    @Column(name = "created")
    public Date created;

    public CategorySMSMapping() {
        super();
    }

    public CategorySMSMapping(Category cat, Integer sms_id, String sms_sender, String sms_msg) {
        super();
        this.cat = cat;
        this.sms_id = sms_id;
        this.sms_sender = sms_sender;
        this.sms_msg = sms_msg;
        this.created = new Date();
    }

    public void ClearList () {
        new Delete().from(CategorySMSMapping.class).execute();
    }

    public List<CategorySMSMapping> fromCategory(Category cat) {
        return new Select()
                .from(CategorySMSMapping.class)
                .where("category_id = ?", cat.getId())
                .execute();
    }

    public List<CategorySMSMapping> fullList() {
        return new Select()
                .from(CategorySMSMapping.class)
                .execute();
    }
}
