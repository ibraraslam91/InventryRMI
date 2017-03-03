package com.company;

import java.io.Serializable;

/**
 * Created by Aleson on 1/18/2017.
 */
public class Category implements Serializable {

    private String categoryName;

    public Category() {
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
