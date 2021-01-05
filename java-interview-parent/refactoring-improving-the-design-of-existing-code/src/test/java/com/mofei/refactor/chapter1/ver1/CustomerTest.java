package com.mofei.refactor.chapter1.ver1;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author mofei
 * @version 2020/12/30 10:45
 */
public class CustomerTest {

    private Customer mofei;

    @Before
    public void before() {
        mofei = new Customer("mofei");
    }

    @Test
    public void when_regular_and_2days_then_2charge() {
        Movie movie = getMovie("泰囧", Movie.REGULAR);
        Rental rental = new Rental(movie, 2);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("2"));
        assertTrue(statement.contains("1 frequent renter points"));
        printResult(statement);
    }

    private void printResult(String statement) {
        System.out.println("statement = " + statement);
    }

    private Movie getMovie(String title, int priceCode) {
        return new Movie(title, priceCode);
    }

    @Test
    public void when_regular_and_7days_then_95charge() {
        Movie movie = getMovie("泰囧", Movie.REGULAR);
        Rental rental = new Rental(movie, 7);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("9.5"));
        assertTrue(statement.contains("1 frequent renter points"));
    }

    @Test
    public void when_children_and_7days_then_21charge() {
        Movie movie = getMovie("儿童乐园", Movie.CHILDRENS);
        Rental rental = new Rental(movie, 7);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("21"));
        assertTrue(statement.contains("1 frequent renter points"));
    }

    @Test
    public void when_children_and_2days_then_6charge() {
        Movie movie = getMovie("儿童乐园", Movie.CHILDRENS);
        Rental rental = new Rental(movie, 2);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("6"));
        assertTrue(statement.contains("1 frequent renter points"));
    }

    @Test
    public void when_new_release_and_3days_then_1_5charge() {
        Movie movie = getMovie("儿童乐园", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie, 3);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("1.5"));
        assertTrue(statement.contains("2 frequent renter points"));
    }

    @Test
    public void when_new_release_and_7days_then_7_5charge() {
        Movie movie = getMovie("儿童乐园", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie, 7);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("7.5"));
        assertTrue(statement.contains("2 frequent renter points"));
    }

    @Test
    public void when_new_release_and_9days_then_10_5charge() {
        Movie movie = getMovie("儿童乐园", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie, 9);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("10.5"));
        assertTrue(statement.contains("2 frequent renter points"));
    }

    @Test
    public void when_error_rented_days_then_0charge() {
        Movie movie = getMovie("儿童乐园", 10);
        Rental rental = new Rental(movie, 1);
        mofei.addRental(rental);
        String statement = mofei.statement();
        assertTrue(statement.contains("0"));
        assertTrue(statement.contains("1 frequent renter points"));
    }


}