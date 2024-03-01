import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Task1
        System.out.println("Task 1:");
        System.out.println(evaluateExpression("5+20-8+5"));
        System.out.println(evaluateExpression("0+0-0+0"));
        System.out.println(evaluateExpression("7-23-8+5+6"));
        System.out.println();

        //Task2
        System.out.println("Task 2:");
        System.out.println(numberOfHappyStrings(List.of("abc", "b", "bac", "bbb", "aca")));
        System.out.println(numberOfHappyStrings(List.of("abbcc", "abc", "abcabc", "cabcbb")));
        System.out.println();

        //Task3
        System.out.println("Task 3:");
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(reverseList(node1));
        System.out.println(reverseList(node5));
        System.out.println();

        //Task4
        System.out.println("Task4");
        int[] nums1 = new int[]{1, 2, 3, 3, 4, 5};
        int[] nums2 = new int[]{3, 4, 4, 5, 6, 7};
        System.out.println(Arrays.toString(findIntersection(nums1, nums2)));
        System.out.println();


        //Task5
        System.out.println("Task5");
        int[] arr1 = new int[]{6, 2, 2, 3, 4, 1};
        int[] arr2 = new int[]{10, 5, 2, 7, 1, 9};
        int[] arr3 = new int[]{1, 1, 5, 4, 5};
        System.out.println(lenOfLongSubarr(arr1, 8));
        System.out.println(lenOfLongSubarr(arr2, 15));
        System.out.println(lenOfLongSubarr(arr3, 3));
        System.out.println();


        //Task6
        System.out.println("Task 6:");
        int[] array1 = new int[]{5, 1, 22, 25, 6, -1, 8, 10};
        int[] array2 = new int[]{1, 6, -1, 10};
        int[] array3 = new int[]{1, -1, 6, 10};
        System.out.println(isValidSequence(array1, array2));
        System.out.println(isValidSequence(array1,array3));

    }

    //Task1
    //It's better than using stack because -> Time complexity O(N) &  Space Complexity O(1)
    public static int evaluateExpression(String expression) {
        if (expression == null || expression.isEmpty())
            return 0;

        int length = expression.length();
        int res = 0;
        long prevVal = 0;
        char operator = '+';
        int index = 0;

        while (index < length) {
            long currVal = 0;
            while (index < length && Character.isDigit(expression.charAt(index))) {
                currVal = currVal * 10 + (expression.charAt(index) - '0');
                index++;
            }
            if (operator == '+') {
                res += prevVal;
                prevVal = currVal;
            } else if (operator == '-') {
                res += prevVal;
                prevVal = -currVal;
            }
            if (index < length) {
                operator = expression.charAt(index);
                index++;
            }
        }
        res += prevVal;
        return res;
    }

    //Task2
    public static int numberOfHappyStrings(List<String> strings) {
        int ans = 0;
        for (String str : strings) {
            boolean isHappy = true;
            for (int i = 0; i < str.length() - 1; i++)
                if (str.charAt(i) == str.charAt(i + 1)) {
                    isHappy = false;
                    break;
                }
            if (isHappy)
                ans++;
        }
        return ans;
    }

    //Task3
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode ans = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return ans;
    }

    //Task4
    public static int[] findIntersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int num : nums1)
            set.add(num);
        for (int num : nums2)
            if (set.contains(num))
                intersect.add(num);
        int[] ans = new int[intersect.size()];
        int i = 0;
        for (int num : intersect)
            ans[i++] = num;
        return ans;
    }

    //Task5
    public static int lenOfLongSubarr(int[] array, int k) {
        int[] dp = new int[1001];

        for (int val : array) {
            for (int j = k - 1; j > 0; j--) {
                int currSum = j + val;
                if (dp[j] != 0 && currSum <= k)
                    dp[currSum] = Math.max(dp[currSum], 1 + dp[j]);
            }
            dp[val] = Math.max(dp[val], 1);
        }
        return dp[k] == 0 ? -1 : dp[k];
    }


    //Task6
    static boolean isValidSequence(int[] array, int[] sequence) {
        int cntr = 0;
        for (int i = 0; i < array.length && cntr < sequence.length; i++)
            if (sequence[cntr] == array[i])
                cntr++;

        return cntr == sequence.length;
    }

}
