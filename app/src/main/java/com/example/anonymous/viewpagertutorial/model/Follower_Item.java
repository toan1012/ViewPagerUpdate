package com.example.anonymous.viewpagertutorial.model;

/**
 * Created by Anonymous on 12/1/2017.
 */

public class Follower_Item {
    private String originalPrice;
    private String salePrice;
    private int pictureItem;
    private boolean isPressedCart;
    private String nameItem;

    public Follower_Item(String originalPrice, String salePrice, int pictureItem, String nameItem) {
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pictureItem = pictureItem;
        this.nameItem = nameItem;
        this.isPressedCart = false;
    }

    @Override
    public String toString() {
        return "Follower_Item{" +
                "originalPrice='" + originalPrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", pictureItem=" + pictureItem +
                ", isPressedCart=" + isPressedCart +
                ", nameItem='" + nameItem + '\'' +
                '}';
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public int getPictureItem() {
        return pictureItem;
    }

    public void setPictureItem(int pictureItem) {
        this.pictureItem = pictureItem;
    }

    public boolean isPressedCart() {
        return isPressedCart;
    }

    public void setPressedCart(boolean pressedCart) {
        isPressedCart = pressedCart;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }
}
