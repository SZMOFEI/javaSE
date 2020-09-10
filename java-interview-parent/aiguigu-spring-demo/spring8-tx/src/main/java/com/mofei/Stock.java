package com.mofei;

/**
 * @author mofei
 * @date 2020/9/10 20:34
 */
public class Stock {
    private String isbn;
    private int stock ;

    public Stock() {
    }

    public Stock(String isbn, int stock) {
        this.isbn = isbn;
        this.stock = stock;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
