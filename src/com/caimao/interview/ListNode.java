package com.caimao.interview;

public class ListNode {
    public int index;
    public ListNode nextNode;

    public ListNode reverseList(ListNode pHead){
        ListNode pReverseHead = null;//反转过后的单链表存储头节点
        ListNode pNode = pHead;//定义pNode的下一个节点
        ListNode pPrev = null;//定义存储签一个节点
        while(pNode != null){
            ListNode pNext = pNode.nextNode;//定义pNext指向pNode的下一个节点
            if(pNext != null){//如果pNode的下一个节点为空，则pNode即为结果
                pReverseHead = pNode;
            }
            pNode.nextNode = pPrev;//修改pNode的指针域指向pPrev
            pPrev = pNode;//将pNode节点复制给pPrev
            pNode = pNext;//将pNode的下一个节点复制给pNode
        }
        return pReverseHead;
    }

    public ListNode reverseList3(ListNode  pHead){
        if(pHead == null || pHead.nextNode == null) {//如果没有节点或只有一个节点直接返回pHead
            return pHead;
        }
        ListNode pNext = pHead.nextNode;//保存当前节点的下一个节点
        pHead.nextNode = null;//打断当前节点的指针域
        ListNode reverseHead = reverseList3(pNext);//递归结束后reverseHead一定是新链表的头节点
        pNext.nextNode = pHead;//修改指针域
        return reverseHead;
    }
}
