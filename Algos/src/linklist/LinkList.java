package linklist;

public class LinkList {

	Node head;
	int nodeData = 0;
	
	public Node addNode(int val){
		Node ptr;
		Node temp = head;
		if(head == null){
			head = new Node(val);
			
		}
		else{
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new Node(val);
		}
		return head;
	}
	
	public void displayList(){
		Node temp;
		temp = head;
		while(temp.next != null){
			System.out.println("data::"+temp.data);
			temp = temp.next;
		}
		System.out.println("data::"+temp.data);
		
	}
	
	private int isPalindromeLinkList(Node first){
		Node fastPtr = first;
		Node slowPtr = first;
		Node mid = null;
		Node slowPtr_prev = null;
		int res;
		//find the middle element. Prev node of slow ptr stored to construct original list later
		while(fastPtr.next != null && fastPtr != null){
			fastPtr = fastPtr.next.next;
			slowPtr_prev = slowPtr;
			slowPtr = slowPtr.next;  //slowPtr gives middle element
		}
		
		if(fastPtr != null){  //this is when linklist is of size odd
			mid = slowPtr;
			slowPtr = slowPtr.next;
		}
		
		reverse(slowPtr);
		res = isSame(first,slowPtr);
		
		
		//construct original
		reverse(slowPtr);
		if(mid != null){
			slowPtr_prev.next = mid;
			mid.next = slowPtr;
		}
		else{
			slowPtr_prev.next = slowPtr;
		}
		return res;

		
	}
	
	private int isSame(Node first, Node slowPtr) {
		// TODO Auto-generated method stub
		Node head1 = first;
		Node head2 = slowPtr;
		while(head1 != null && head2 != null){
			if(head1.data == head2.data){
				head1 = head1.next;
				head2 = head2.next;
			}
			else
				return 0;
		}
		
		
		if(head1 == null && head2 == null){
			return 1;
		}
		else
			return 0;
	}

	private void reverse(Node slowPtr) {
		// TODO Auto-generated method stub
		Node prev = null;
		Node curr = slowPtr;
		Node next;
		
		while(curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		
		slowPtr = prev;
		while(slowPtr!=null){
			System.out.println(slowPtr.data);
			slowPtr = slowPtr.next;
		}
		
		
	}

	public static void main(String[] args){
		//Node first = new Node(1);
		LinkList list = new LinkList();
		Node first = list.addNode(1);
		list.addNode(2);
		list.addNode(3);
		list.addNode(4);
		
		list.displayList();
	}
	
	
}

class Node {
	int data;
	Node next = null;
	
	Node(int d){
		data = d;
	}
	
	
}
