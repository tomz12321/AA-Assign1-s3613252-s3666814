/**Implement Doubly Linked List
 * 
 * The operations follow the Lab_Week2 solution
 * modified the certain data type into generic
 * - add
 * - search
 * - removeOne
 * - removeAll
 * - print
 * 
 * Author : Jyh-woei Yang (s3613252) (Tom)
 */

package javaSrc;

import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T extends Comparable<T>> extends Multiset<T> //extends Comparable interface
{
	protected Node<T> mHead; //reference to head of list
	protected Node<T> mTail; //reference to tail of list
	protected int mLength;
	
	public LinkedListMultiset() {
		// Implement me!
		mHead = null;
		mTail = null;
		mLength = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		// Implement completed by Tom
		Node<T> newNode = new Node<T>(item);
		
		//If head is empty
		if (mHead == null) {
			mHead = newNode;
			mTail = newNode;
		}
		//else, add node to the head of list.
		else {
			// scan the list to check if there is duplicate item
			int found = 0;
			Node<T> currNode = mHead;
			while (currNode != null) {
				if (currNode.getmValue().compareTo(item) == 0) {
					currNode.increaseFound();
					found = currNode.getmNumber();
					break;
				}
				currNode = currNode.getmNext();
			}
			
			if(found < 1)
			{
				//if it's a empty list
				mTail.setmNext(newNode);
				newNode.setmPrev(mTail);
				mTail = newNode;
			}
			else
			{
				newNode = null;
			}
		}
		
		mLength++;
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!		
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement completed by Tom
		if(mHead != null)
		{
			Node<T> currNode = mHead;
			
			//check if value is head node or not
			if(currNode.getmValue().compareTo(item) == 0) {
				// check if length of 1
				if (mLength == 1) {
					mHead = mTail = null;
				}
				else {
					if(currNode.getmNumber() == 1)
					{
						mHead = currNode.getmNext();
						mHead.setmPrev(null);
						currNode = null;
					}
					else 
						currNode.decreaseFound();
				}
				mLength--;
				
			}
			
			//search for value in the rest of list
			else {
				currNode = currNode.getmNext();
				
				while (currNode != null) {
					if (currNode.getmValue().compareTo(item) == 0) {
						//check number
						if(currNode.getmNumber() <= 0)
							break;
						
						if(currNode.getmNumber() == 1)
						{
							Node prevNode = currNode.getmPrev();
							prevNode.setmNext(currNode.getmNext());
							//if failed
							if (currNode.getmNext() != null) {
								currNode.getmNext().setmPrev(prevNode);
							}
							else {
								mTail = prevNode;
							}
							currNode = null;
						}
						else
							currNode.decreaseFound();
						mLength--;
						break;
					}
					else
						currNode = currNode.getmNext();
				}
			}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement completed by Tom
		Node<T> currNode = mHead;
		
		//check if value is head node
		if (currNode.getmValue().compareTo(item) == 0) {
			//check if length of 1
			if (mLength == 1) {
				mHead = mTail = null;
				mLength--;
			}
			else {
				int number = currNode.getmNumber();
				mLength -= number;
				mHead = currNode.getmNext();
				mHead.setmPrev(null);
				currNode = null;	
			}
		}
		// search for value in rest of list
		else {
			currNode = currNode.getmNext();
			
			while (currNode != null) {
				if (currNode.getmValue().compareTo(item) == 0) {
					int number = currNode.getmNumber();
					mLength -= number;
					Node prevNode = currNode.getmPrev();
					prevNode.setmNext(currNode.getmNext());
					//check if tail
					if(currNode.getmNext() != null) {
						currNode.getmNext().setmPrev(prevNode);
					}
					currNode = null;
				}
				else
					currNode = currNode.getmNext();
			}
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement completed by Tom
		out.println(toString());
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
		private Node<T> mPrev; // stored value of the same value
		private int mNumber;
		
		public Node(T value) {
			mValue = value;
			mNext = null;
			mPrev = null;
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

		public Node<T> getmPrev() {
			return mPrev;
		}

		public void setmPrev(Node<T> mPrev) {
			this.mPrev = mPrev;
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
	
} // end of class LinkedListMultiset