package com.philip;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class TestJava8 {
    static List<Integer> list;


    static Integer generateRandomInteger(){
        Double d = (Double)Math.random()*100d;
        return Integer.valueOf(d.intValue());
    }


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        list = Arrays.asList(generateRandomInteger(),generateRandomInteger(),generateRandomInteger(),generateRandomInteger());
        list.stream().forEach(System.out::println);
        System.out.println("====================>");
    }

    @Test
    public void testMethod1(){
        //Find sum
        int sum = list.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        }).get();
        System.out.println("Sum value is:"+sum);

        sum = list.stream().mapToInt(Integer::intValue).reduce(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        }).getAsInt();
        System.out.println("Sum value is:"+sum);

        sum = list.stream().mapToInt(Integer::intValue).reduce(0, (a, b) -> a +b);
        System.out.println("Sum value is:"+sum);

        sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum value is:"+sum);
        //find max
        int maxValue = list.stream().reduce(12, (a, b) -> {
            return a.compareTo(b)>0?a:b;});
        System.out.println("Max value is:"+maxValue);

        maxValue = list.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.println("Max value is:"+maxValue);

        System.out.println("====================>");

        //convert to another type list by map()
        System.out.println("====================> by map()");
        list.stream().map(integer -> {return new Order(integer, 1);}).forEach(System.out::println);

        //study how to use reduce() to represents map()
        List<Order> orders = new ArrayList<>();
        orders = list.stream().reduce(
                orders,
                (u, t) -> {
                    u.add(new Order(t, 1));
                    return u;
                },
                (a, b) -> a
        );
        System.out.println("====================> by reduce()");
        orders.forEach(System.out::println);

        //the other usage of reduce
        list = new ArrayList<>();
        int identity = 12;
        int newSum = list.stream().reduce((i1, i2) -> i1+i2).orElse(identity).intValue();
        System.out.println("Sum value by Optional.orElse() is:"+newSum);

        //this could be any value, 0 or 12 if stream is not empty,

        newSum = list.stream().reduce(identity, (i1, i2) -> i1+i2).intValue();
        System.out.println("Sum value by Optional.orElse() is:"+newSum);

        //is the same as previous
        newSum = list.stream().reduce(identity, (i1, i2) -> i1+i2, (a,b)->a).intValue();
        System.out.println("Sum value by Optional.orElse() is:"+newSum);
    }

    class Order{
        private int orderNo;
        private int orderType;
        Order(int orderNo, int orderType){
            this.orderNo=orderNo;
            this.orderType=orderType;
        }
        @Override
        public String toString(){
            return "This is order["+orderType+", "+orderNo+"].";
        }
    }

}

