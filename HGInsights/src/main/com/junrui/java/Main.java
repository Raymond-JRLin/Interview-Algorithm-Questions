package main.com.junrui.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

}

class MyRandom {
    Random rand = new Random();

    List<Integer> getRandInt(int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(rand.nextInt());
        }
        return list;
    }

    List<Integer> getRandInt(int k, int seed) {
        rand = new Random(seed);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(rand.nextInt());
        }
        return list;
    }
}
