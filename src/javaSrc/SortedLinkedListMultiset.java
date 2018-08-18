import java.io.PrintStream;
import java.util.*;

//import javaSrc.LinkedListMultiset.Node;

//import SimpleLinkedList.Node;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	protected Node mHead;
	protected int mLength;
	
	public SortedLinkedListMultiset() {
		// Implement completed by Tom
		mHead = null;
		mLength = 0;
		
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		// Implement completed by Tom
		Node<T> newNode = new Node(1);
		
		//If head is empty, then list is empty and head reference need to be initialised.
		//if (mHead == null || item.compareTo(mHead.getValue()) < 0 )
		if (mHead == null) {
				newNode.setmNext(mHead);
			mHead = newNode;
			mLength ++;
		}
		// otherwise, check if the item exists in the list.
		else {
			Node<T> currNode = mHead;
			//while ( currNode.getNext()!=null && item.compareTo(currNode.getNext().getValue())>=0 )
			while ( currNode.getmNext()!=null) 
			{
            	currNode = currNode.getmNext();
            }
            // currNode: the place to insert new element or increase count
           
			if (currNode.getmValue().equals(item)) {
				//currNode.increaseCount();
				currNode.increaseFound();
			} else if (currNode.getmNext() == null) {
				newNode.setmNext(null);
				currNode.setmNext(newNode);
			} else {
				newNode.setmNext(currNode.getmNext());
				currNode.setmNext(newNode);
			}
			mLength ++;
		}
			
			
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!		
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
	} // end of print()
	
	/**
	 *  @return String representation of the list.
	 */
	public String toString() {
		Node<T> currNode = mHead;
		
		StringBuffer str = new StringBuffer();
		int found = 0;
		
		while (currNode != null) {
			str.append(currNode.getmValue() + printDelim + currNode.getmNumber() + "\n");
			currNode = currNode.getmNext();
		}
		
		return str.toString();
	}
	
	/**
	 * Node type, inner private class. 
	 */
	private class Node<T>
	{
		private T mValue; //stored value of node
		private Node<T> mNext; //reference to next node
		private int mNumber; //found number
		
		public Node(T value) {
			mValue = value;
			mNext = null;
			mNumber = 1;
		}
		public T getmValue() {
			return mValue;
		}

		public void setmValue(T mValue) {
			this.mValue = mValue;
		}

		public Node<T> getmNext() {
			return mNext;
		}

		public void setmNext(Node<T> mNext) {
			this.mNext = mNext;
		}

		// return found number
		public int getmNumber() {
			return mNumber;
		}

		public void setmNumber(int mNumber) {
			this.mNumber = mNumber;
		}
		
		public void increaseFound() {
			mNumber++;
		}
		public void decreaseFound() {
			mNumber--;
		}
	} // end of inner class Node
	
} // end of class SortedLinkedListMultiset
