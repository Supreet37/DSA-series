import java.util.ArrayList;
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
public class DailyStreak2 {
  public int pairSum(ListNode head){
    ArrayList<Integer> list = new ArrayList<>();

    while(head != null){
      list.add(head.val);
      head = head.next;
    }

    int maxSum = 0;
    int left = 0;
    int right = list.size() - 1;

    while(left < right){
      maxSum = Math.max(maxSum,list.get(left) + list.get(right));
      left++;
      right--;
    }
    return maxSum;
  }
  public static void main(String[] args) {
      DailyStreak2 ds = new DailyStreak2();
      ListNode head = new ListNode(1);
      head.next = new ListNode(9);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      System.out.println(ds.pairSum(head));
  }
}


/* BETTER APPROACH FOR 3MS
class Solution {
    public int pairSum(ListNode head) {
        int size=0;
        ListNode temp=head;
        while(temp!=null){
        size++;
        temp=temp.next;
        }
        temp=head;
        int k=0;
        int[] ans=new int[size];
        while(temp!=null){
            ans[k++]=temp.val;
            temp=temp.next;
        }
        int i=0;
        int j=size-1;
        int max=0;
        while(i<=j){
           int sum=ans[i]+ans[j];
           if(sum>max){
            max=sum;
           }
            i++;
            j--;
        }
        return max;
        
    }
}
 */