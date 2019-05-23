/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

/**
 *
 */
package com.example.hala2.al_sharog;

import java.util.ArrayList;

/**
 * The Class Product used as model for Products.
 *
 * @author Hitesh
 */
public class Book {


    private int Id;
    private String Name;

    private String Price;

    private String Image;
    private int Quantity=0;



    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }




    public Book() {
    }

    public Book(int id, String name, String price, String image, int quantity) {
        Id = id;
        Name = name;
        Price = price;
        Image = image;
        Quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
