package com.mofei.refactor.chapter1.ver1;

/**
 * @author mofei
 * @version 2020/12/30 10:41
 */
import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    //生成详单的函数
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            switch (each.getMovie().get_priceCode()) {
                //用例1 ，常规书籍，小于等于2天，那么费用就是2
                //用例2， 常规书籍，大于2天， 那么费用就是2+(i-2)*1.5
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.CHILDRENS:
                    //用例3，儿童书籍， 租期7天 费用21
                    //用例4，儿童书籍，租期2天，费用6元
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.NEW_RELEASE:
                    //用例5 ，新发布书籍， 小于等于3天， 费用1.5元
                    //用例6 ， 新发布书籍 ， 大于3天 ， 7天， 费用是1.5+(7-3)*1.5 = 7.5元
                    //用例7， 新发布书籍  ， 9天， 费用是1.5+（9-3）*1.5=10.5
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
                    //用例8 ， 错误的技术类型， 0元输出
                default:
                    break;
            }

            // add grequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().get_priceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // show fingures for this rental
            result += "\t" + each.getMovie().get_title() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}
