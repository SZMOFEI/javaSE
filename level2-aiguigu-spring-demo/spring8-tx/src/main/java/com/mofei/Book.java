package com.mofei;

/**
 * @author mofei
 * @version 2020/9/10 19:54
 */
public class Book {
    private String isbn;
    private String book_name;
    private int price;

    public Book() {
    }

    public Book(String isbn, String book_name, int price) {
        this.isbn = isbn;
        this.book_name = book_name;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
