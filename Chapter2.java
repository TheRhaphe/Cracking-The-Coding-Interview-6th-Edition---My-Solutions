import java.util.*;
public class Chapter2 {
	public static void main(String[] args) {
		testRemoveDuplicates();
		System.out.println("");
		
		testRemoveDuplicates2();
		System.out.println("");
		
		printList(createList(6));
		testFindKthElement(createList(6), 4);
		
		ListNode myList = createList(6);
		ListNode middleNode = myList.next.next.next;
		System.out.print("OG list = ");
		printList(myList);
		System.out.print(" after deleteMidNode List = ");
		deleteMiddleNode(middleNode);
		printList(myList);
	}
	public static void removeDuplicates(ListNode node) {
		ListNode currentNode = node, prevNode=null;
		HashSet<Integer> record = new HashSet<>();
		while(currentNode!=null) {
			if(record.contains(currentNode.data)) {
				prevNode.next=currentNode.next;
			}else {
				record.add(currentNode.data);
				prevNode=currentNode;
			}
			currentNode=currentNode.next;
		}
	}
	public static void removeDuplicates2(ListNode node) {
		ListNode currentNode = node, prevNode=null;
		while(currentNode!=null) {
			ListNode runner = currentNode.next;
			boolean deleted = false;
			while(runner!=null) {
				if(runner.data==currentNode.data) {
					prevNode.next=currentNode.next;
					deleted=true;
					break;
				}
				runner = runner.next;
			}
			if(!deleted)prevNode=currentNode;
			currentNode=currentNode.next;
		}
	}
	public static ListNode findKthElement(ListNode list, int k){
		int listSize = 0;
		ListNode runner = list;
		while(runner!=null){
			listSize++;
			runner=runner.next;
		}
		if(k>=listSize){
			throw new IndexOutOfBoundsException();
		}else{
			ListNode temp = list;
			for(int a=1; a<listSize-k; a++){
				temp = temp.next;
			}
			return temp;
		}
	}
	public static void deleteMiddleNode(ListNode middleNode) {
		middleNode.data = middleNode.next.data;
		middleNode.next = middleNode.next.next;
	}
	
	//###### My helper methods
	public static void testFindKthElement(ListNode node, int k) {
		System.out.println(k+"th element from last is = "+findKthElement(node,k).data);
	}
	public static void testRemoveDuplicates() {
		ListNode mylist = createListWithDups(7);
		System.out.print("OG List = ");
		printList(mylist);
		System.out.print(" Without duplicates = ");
		removeDuplicates(mylist);
		printList(mylist);
	}
	public static void testRemoveDuplicates2() {
		ListNode mylist = createListWithDups(7);
		System.out.print("OG List = ");
		printList(mylist);
		System.out.print(" Without duplicates = ");
		removeDuplicates2(mylist);
		printList(mylist);
	}
	
	public static void printList(ListNode node) {
		System.out.print("{ ");
		while(node!=null) {
			System.out.print(node.data+" ");
			node=node.next;
		}
		System.out.print("}");
	}
	private static ListNode createList(int listSize) {
		ListNode prevNode=new ListNode(0);
		ListNode start = new ListNode(0);
		for(int a=0; a<listSize; a++) {
			ListNode n =new ListNode(a);
			if(a==0) {
				start=n;
				prevNode=n;
			}else {
				prevNode.next=n;
				prevNode=prevNode.next;
			}
		}
		return start;
	}
	private static ListNode createListWithDups(int listSize) {
		ListNode prevNode=new ListNode(0);
		ListNode start = new ListNode(0);
		boolean dup = false;
		for(int a=0; a<listSize; a++) {
			ListNode n =new ListNode(a);
			if(a==0) {
				start=n;
				prevNode=n;
			}else {
				prevNode.next=n;
				prevNode=prevNode.next;
			}
			if(a==3&&!dup) {a=0;dup=true;} 
		}
		return start;
	}
	
}
class ListNode{
	int data;
	ListNode next;
	public ListNode(int d) {
		data=d;
	}
}
