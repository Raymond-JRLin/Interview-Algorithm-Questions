public class CanMakeCoffee {
    public static void main(String args[] ) throws Exception {

        List<Integer> coffee1 = Arrays.asList(2, 4, 7);

        List<List<Integer>> result1 = canMakeCoffee(coffee1, 13, 16);
        printCoffeeResult(result1, 13, 16);

        List<Integer> coffee2 = Arrays.asList(3, 6, 9);

        List<List<Integer>> result2 = canMakeCoffee(coffee2, 8, 9);
        printCoffeeResult(result2, 8, 9);

        List<List<Integer>> result3 = canMakeCoffee(coffee2, 10, 11);
        printCoffeeResult(result3, 10, 11);

        List<List<Integer>> result4 = canMakeCoffee(coffee2, 17, 21);
        printCoffeeResult(result4, 17, 21);
    }
    private static List<List<Integer>> canMakeCoffee(List<Integer> coffee, int from, int to) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(coffee, from, to, 0, 0, result, new ArrayList<>());
        return result;
    }
    private static void dfs(List<Integer> coffee, int from, int to, int index, int sum, List<List<Integer>> result, List<Integer> list) {
        // System.out.println("get sum = " + sum);
        if (sum >= from && sum <= to) {
            // System.out.println("found sum = " + sum);
            result.add(new ArrayList<>(list));
        }
        if (sum > to) {
            return;
        }
        if (index == coffee.size()) {
            return;
        }
        for (int i = index; i < coffee.size(); i++) {
            list.add(coffee.get(i));
            dfs(coffee, from, to, i, sum + coffee.get(i), result, list);
            list.remove(list.size() - 1);
        }
    }
    private static void printCoffeeResult(List<List<Integer>> result, int from, int to) {
        if (result.size() == 0) {
            System.out.println("Cannot make coffee in this range [" + from + ", " + to + "] !");
            return;
        }

        System.out.println("Can make coffee in this range [" + from + ", " + to + "], combinations are: ");
        for (List<Integer> list : result) {
            printList(list);
        }
    }
}
