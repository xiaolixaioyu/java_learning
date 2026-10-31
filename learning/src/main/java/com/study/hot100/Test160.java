package com.study.hot100;
// 1. 相交链表
/*
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
如果两个链表不存在相交节点，返回 null
 */
public class Test160 {
    public static void main(String[] args) {
        // ========== 测试用例1：两个链表存在相交节点 ==========
        // 构造公共相交部分：c1 -> c2 -> null
        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(10);
        c1.next = c2;

        // 链表A：a1 -> a2 -> c1 -> c2
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        a1.next = a2;
        a2.next = c1;

        // 链表B：b1 -> b2 -> b3 -> c1 -> c2
        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(1);
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;

        Solution solution = new Solution();
        ListNode intersect1 = solution.getIntersectionNode(a1, b1);
        System.out.println("用例1相交节点val：" + (intersect1 == null ? "无相交" : intersect1.val));

        // ========== 测试用例2：两个链表没有相交 ==========
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;

        ListNode m1 = new ListNode(3);
        ListNode m2 = new ListNode(4);
        m1.next = m2;

        ListNode intersect2 = solution.getIntersectionNode(n1, m1);
        System.out.println("用例2相交节点val：" + (intersect2 == null ? "无相交" : intersect2.val));
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
         next = null;
      }
  }
class Solution {
    // 时间复杂度太高，没法处理大规模数据
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //找相交的节点
        ListNode pA = headA, pB = headB;
        ListNode res=null;
        //最好用双重循环 避免遗漏情况
        while(pB!=null){
            pA = headA;
            while(pA!=null){
                if(pA.equals(pB)){
                    res = pA;
                    return res;
                }else{
                    pA = pA.next;
                }
            }
            pB = pB.next;
        }
        return res;
    }
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        //双指针法 如果有相交的地方迟早会相遇  无交点一定会在null相遇
        ListNode pA = headA, pB = headB;
        while(pA!=pB){
            pA=pA==null ?headB :pA.next;//等于一个三元表达式的结果
            pB=pB==null ? headA : pB.next;
        }
        return pA;
    }

}
