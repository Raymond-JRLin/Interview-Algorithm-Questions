import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        System.out.println("Hello World");

        Transaction aBegin = new Transaction(0, 10, -1, 0, false);
        Transaction aEnd = new Transaction(0, 10, -1, 1, false);
        Transaction bBegin = new Transaction(1, 20, 2, 0, true);
        Transaction bEnd = new Transaction(1, 20, 2, 1, true);
        Transaction cBegin = new Transaction(2, 30, -1, 0, false);
        Transaction cEnd = new Transaction(2, 30, -1, 1, false);

        List<Transaction> list = Arrays.asList(new Transaction[]{aBegin, bBegin, cBegin, cEnd, bEnd, aEnd});
        int[] nums = new int[]{1, 2, 3};
        boolean finish = makeTransactions(nums, list);
        if (!finish) {
            System.out.println("Rollback!");
        }
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    private static boolean makeTransactions(int[] nums, List<Transaction> transactions) {
        Map<Integer, Integer> map = new HashMap<>(); // <index, value> after transaction
        Stack<Integer> stack = new Stack<>(); // <index of transactions>
        for (int i = 0; i < transactions.size(); i++) {
            // assume transactions are valid and legal
            Transaction trans = transactions.get(i);
            if (trans.isRollback) {
                return false; // rollback , cancel all
            }
            if (trans.type == 0) {
                // begin a new transaction
                stack.push(i);
            } else {
                // end a transaction
                Transaction prev = transactions.get(stack.pop());
                int value = prev.changeFrom == -1 ? nums[prev.index] : map.get(prev.changeFrom);
                map.put(prev.index, value + prev.value);
            }
        }
        // make transactions
        for (int key : map.keySet()) {
            nums[key] = map.get(key);
        }
        return true;
    }
}
class Transaction {
    int index; // which index in array we'd like to make
    int value;
    int changeFrom; // if it change from another index of array
    int type; // 0 is begin, 1 is end
    boolean isRollback;

    Transaction(int index, int value, int changeFrom, int type, boolean isRollback) {
        this.index = index;
        this.value = value;
        this.changeFrom = changeFrom;
        this.type = type;
        this.isRollback = isRollback;
    }
}
