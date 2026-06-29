/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 import java.util.ArrayList;
class Solution {
    public int pairSum(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        int maxSum = 0;
        int left = 0;
        int right = list.size() - 1;

        while(left<right){
            maxSum = Math.max(maxSum,list.get(left)+list.get(right));
            left++;
            right--;

        }
        return maxSum;
    }
}