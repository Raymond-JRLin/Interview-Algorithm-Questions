package main.com.junrui.java;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Test()
public class test {
    @DataProvider
    public Object[][] data() {
        return new String[][] {new String[] {"data1"}, new String[] {"data2"}};
    }

    @Test(dataProvider = "data")
    public void test(String d) {
        Assert.assertEquals("First Line>\nSecond Line", "Third Line\nFourth Line");
    }

    @Test(dataProvider = "data")
    public void test2(String d) {
        Assert.assertEquals("data2", "data2");
    }

    @Test
    public void test3(String d) {
        assert d.equalsIgnoreCase("it's a test");
    }

    @Test
    public void randTest() {


//        List<Integer> randList = rand.getRandInt(3);

        List<Integer> expected = Arrays.asList(-1155099828, -1879439976, 304908421);
        for (int i = 0; i < 3; i++) {
            MyRandom rand = new MyRandom();
            List<Integer> seedList = rand.getRandInt(3, 3);
//            printList(seedList);
            assert seedList.equals(expected);
        }


//        printList(randList);

    }

    private void printList(List<Integer> list) {
        for (int num :
                list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}