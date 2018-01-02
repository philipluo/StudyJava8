package com.philip;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelTest {

    @Test
    public void testIntegerArrys(){
//        IntStream.range(1,3).forEach(System.out::println);
//        System.out.println(IntStream.range(1,3).summaryStatistics().getAverage());
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
        list.stream()
                .map(integer -> integer.longValue())
                .reduce(new ArrayList<>(),
                        new BiFunction<ArrayList<Order>, Long, ArrayList<Order>>() {
                            @Override
                            public ArrayList<Order> apply(ArrayList<Order> orders, Long aLong) {
                                ArrayList<Order> list = new ArrayList<>(orders);
                                list.add(new Order(aLong.intValue()));
                                return list;
                            }
                        }, new BinaryOperator<ArrayList<Order>>() {
                            @Override
                            public ArrayList<Order> apply(ArrayList<Order> orders, ArrayList<Order> orders2) {
                                return orders;
                            }
                        }).forEach(System.out::println);

        list.stream()
                .reduce(new ArrayList<Order>(),
                        (olist, i) -> {
                            olist.add(new Order(i));
                            return olist;
                        }
                        ,
                        (alist, blist) -> alist)
                .forEach(System.out::println);

        list.stream()
                .map(i -> {return new Order(i);})
                .forEach(System.out::println);;
    }

    class Order{
        int orderNo;

        public Order(int orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderNo=" + orderNo +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return orderNo == order.orderNo;
        }

        @Override
        public int hashCode() {

            return Objects.hash(orderNo);
        }
    }


}
