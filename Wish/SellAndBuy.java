import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class WishSellAndBuy {
    public static void main(String args[] ) throws Exception {
        sellAndBuy();
    }

    private static void sellAndBuy() {
        Store store = new Store();
        System.out.println(store.sell(1, 1.5));
        System.out.println(store.sell(1, 1.4));
        System.out.println(store.buy(1, 1.51));
        System.out.println(store.buy(1, 1.31));
        System.out.println(store.buy(1, 1.6));
        System.out.println(store.sell(1, 1.2));
    }
    // 只有一个产品，有很多不同的价格。sell输入的是想要卖出此产品的数量和能接受的最低价格，如果系统里面没有任何buy的订单比这个最低价格高，就直接返回0。如果有buy的订单，返回能卖出去的数量剩下的sell存下来，后面有buy的订单再去看是否可以卖出去。
    // sell（1，1.5）（返回0）
    // sell（1，1.4）（返回0）
    // buy（2，1.51）（返回2）

    private static class Store {

        static PriorityQueue<Order> buys;
        static PriorityQueue<Order> sells;

        Store() {
            buys = new PriorityQueue<>();
            sells = new PriorityQueue<>();
        }

        public static int sell(int num, double price) {
            int result = 0;
            while (!buys.isEmpty() && buys.peek().price >= price && num > 0) {
                Order curr = buys.poll();
                if (curr.count <= num) {
                    num -= curr.count;
                    result += curr.count;
                } else {
                    result += num;
                    Order rest = new Order(curr.count - num, curr.price);
                    buys.offer(rest);
                    num = 0;
                }
            }
            if (num != 0) {
                sells.offer(new Order(num, price));
            }
            return result;
        }

        public static int buy(int num, double price) {
            int result = 0;
            while (!sells.isEmpty() && sells.peek().price <= price && num > 0) {
                Order curr = sells.poll();
                if (curr.count <= num) {
                    num -= curr.count;
                    result += curr.count;
                } else {
                    result += num;
                    Order rest = new Order(curr.count - num, curr.price);
                    sells.offer(rest);
                    num = 0;
                }
            }
            if (num != 0) {
                buys.offer(new Order(num, price));
            }
            return result;
        }
    }
    private static class Order implements Comparable<Order> {
        int count;
        double price;

        public Order(int c, double p) {
            this.count = c;
            this.price = p;
        }

        @Override
        public int compareTo(Order o2) {
            return Double.compare(this.price, o2.price);
        }
    }
}
