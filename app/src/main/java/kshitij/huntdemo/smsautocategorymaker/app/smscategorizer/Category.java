package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Category")
public class Category extends Model {
    // This is a regular field
    @Column(name = "catName", unique = true)
    public String name;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Category() {
        super();
    }

    public Category(String name) {
        super();
        this.name = name;
    }

    public String getCategoryName(Category cat) {
        return cat.name;
    }

    public List<Category> categoryList() {
        return new Select()
            .from(Category.class)
            .execute();
    }

    public void SaveList(String[] catList) {
        for (int i = 0; i < catList.length; i++) {
            Category c = new Category();
            c.name = catList[i];c.save();
        }
    }

    public Category fromStringName(String catName) {
        return new Select()
                .from(Category.class)
                .where("catName = ?", catName)
                .executeSingle();
    }

    public void ClearList () {
        new Delete().from(Category.class).execute();
    }
}
