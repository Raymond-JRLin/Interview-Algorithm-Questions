public class SolutionToFindMissingNumber {
    public static void main(String args[] ) throws Exception {
        int[] nums1 = new int[]{0, 1, 3}; // 2
        int[] nums2 = new int[]{-1, 0, 2}; // 1
        int[] nums3 = new int[]{8, 10}; // 9
        int[] nums4 = new int[]{10, 12, 13, 14}; // 11
        int[] nums5 = new int[]{0, 2, 3}; // 1
        int[] nums6 = new int[]{0, 2, 3, 4}; // 1

        System.out.println("missing num: " + findMissingNumber(nums1));
        System.out.println("missing num: " + findMissingNumber(nums2));
        System.out.println("missing num: " + findMissingNumber(nums3));
        System.out.println("missing num: " + findMissingNumber(nums4));
        System.out.println("missing num: " + findMissingNumber(nums5));
        System.out.println("missing num: " + findMissingNumber(nums6));
    }

    private static int findMissingNumber(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int len = mid + 1 - start; // # value from start to mid
            int midValue = nums[start] + len - 1; // expected value at mid index
            // System.out.println("len: " + len + " , " + mid + " => " + nums[mid] + ", expected: " + midValue);
            if (midValue < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return (nums[start] + nums[end]) / 2;
    }

}
