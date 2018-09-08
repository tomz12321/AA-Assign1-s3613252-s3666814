/**Implement Sorted Linked List
 * 
 * The operations included
 * - add
 * - search
 * - removeOne
 * - removeAll
 * - print
 * 
 * Author : Jyh Woei Tom Yang (s3613252) 09/09/2018
 */
import java.io.PrintStream;
import java.util.*;

//import javaSrc.LinkedListMultiset.Node;

//import SimpleLinkedList.Node;

public class SortedLinkedListMultiset<T extends Comparable<T>> extends Multiset<T>
{
	/** Reference to head of list. */
	protected Node<T> mHead;
	/** Reference to tail of list */
	protected Node<T> mTail;
	/** Length of list. */
	protected int mLength;
	
	public SortedLinkedListMultiset() {
		// Implement completed by Tom
		mHead = null;
		mTail = null;
		mLength = 0;
		
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		// Implement completed by Tom
		Node<T> newNode = new Node<T>(item);
		
		//If head is empty, then list is empty and head reference need to be initialised.
		if (mHead == null) {
			mHead = newNode;
			mTail = newNode;
		}
		// otherwise, check if the item exists in the list.
		else {
			Node<T> currNode = mHead;
			Node prevNode = null;

            while (currNode != null) {
            	// item value is smaller than value
            	if(currNode.getmValue().compareTo(item) > 0) {
            		//if(currNode.getmPrev() == null) //marked out for singly linkedlist
            		if(prevNode == null)
            		{
            			// addBefore
            			newNode.setmNext(mHead);
                    	mHead = newNode;
                    	break;
            		}
            		else
            		{
            			newNode.setmNext(currNode);
            			prevNode.setmNext(newNode);
            			break;
            		}
            	}
            	// item value is grater than value
            	else if(currNode.getmValue().compareTo(item) < 0) {
            		if(currNode.getmNext() == null)
            		{
            			// addLast
            			currNode.setmNext(newNode);
            			mTail = newNode;
            			break;
            		}
            	}
            	else {
            		currNode.increaseFound();
            		break;
            	}
            	//new
            	currNode = currNode.getmNext();
            }  
		}
		mLength++;	
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!		
		int found = 0;
		Node<T> currNode = mHead;
		while (currNode != null) {
			if (currNode.getmValue().compareTo(item) == 0) {
				found = currNode.getmNumber();
				return found;
			}
			currNode = currNode.getmNext();
		}
		// default return, please override when you implement this method
		// return 0;
		return found;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement by Tom
		// check the head is not empty
		if(mHead != null)
		{
			Node<T> currNode = mHead;
					
			// check if value is head node
			if (currNode.getmValue().compareTo(item) == 0) {
			// check if length of 1
			if (mLength == 1) {
				mHead = mTail = null;
			}
			else {
				if(currNode.getmNumber() == 1)
				{
					mHead = currNode.getmNext();
			        currNode = null;
			    }
			    else
			        currNode.decreaseFound();
			    }
			            
			    mLength--;
			    }
			    // search for value in rest of list
			    else {
			         //new
			         Node prevNode = null;
                	 currNode = currNode.getmNext();

			         while (currNode != null) 
			         {
				         if (currNode.getmValue().compareTo(item) == 0) 
				         {
					         // check the number
					         if(currNode.getmNumber() <= 0)
					        	 	break;
					                	
					         if(currNode.getmNumber() == 1)
					         {
					         	prevNode = currNode;
					        	prevNode.setmNext(currNode.getmNext());
					            // check if tail
					            if (currNode.getmNext() != null) 
					            {
					            	prevNode = currNode;
					            }
					            else 
					            {
					            		mTail = currNode;
					            }
					            currNode = null;
				         	}
				        	else
				            	currNode.decreaseFound();
				         	mLength--;
				         	break;
				         }
				         else
				         {
				         	    //new
	                        	prevNode = currNode;
				        	 	currNode = currNode.getmNext();
				         }
			        }	
			    }
			}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement by Tom
		Node<T> currNode = mHead;
		
		// check if value is head node
        if (currNode.getmValue().compareTo(item) == 0) {
            // check if length of 1
            if (mLength == 1) {
                mHead = mTail = null;
                mLength--;
            }
            else {
            	int number = currNode.getmNumber();
            	mLength -= number;
            	mHead = currNode.getmNext();
            	currNode = null;
            }
        }
        // search for value in rest of list
        else {
            Node prevNode = currNode;
            currNode = currNode.getmNext();

            while (currNode != null) {
                if (currNode.getmValue().compareTo(item) == 0) 
                {
                	int number = currNode.getmNumber();
                	mLength -= number;
                    prevNode.setmNext(currNode.getmNext());
                	
                    // check if tail
                    if (currNode.getmNext() != null) {
                    	prevNode = currNode;
                    }
                    else {
                    	mTail = currNode;
                    }
                    currNode = null;
                    break;
                }
                else
                {
                	//new
                    prevNode = currNode;
                	currNode = currNode.getmNext();
                }
            }	
        }
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement by Tom
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
		private Node<T> mNext; //reference to next node.
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
