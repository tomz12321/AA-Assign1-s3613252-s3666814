/**Implement singly Linked List
 * 
 * The operations follow the Lab_Week2 solution
 * modified the certain data type into generic
 * - add
 * - search
 * - removeOne
 * - removeAll
 * - print
 * 
 * Author : Jyh Woei Yang (s3613252) (Tom)
 */

import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T extends Comparable<T>> extends Multiset<T> //extends Comparable interface
{
	/** Reference to head of list. */
    protected Node<T> mHead;
    /** Reference to tail of list. */
    protected Node<T> mTail;
    /** Length of list. */
    protected int mLength;
    
	public LinkedListMultiset() {
		// Implement by Tom Yang
		mHead = null; 
        mTail = null;
        mLength = 0;
	} // end of LinkedListMultiset()
	
	/**
     * Add a new value to the start of the list.
     * (AddLast) 
     * @param newValue Value to add to list.
     */
	public void add(T item) {
		// Implement by Tom Yang
        Node<T> newNode = new Node<T>(item);
        
        // If head is empty, then list is empty and head and tail references need to be initialised.
        if (mHead == null) {
            mHead = newNode;
            mTail = newNode;
        }
        // otherwise, add node to the head of list.
        else {
        	// scan the list to check is there having duplicate item
        	int found = 0;
    		Node<T> currNode = mHead;
    		while (currNode != null) {
            	if (currNode.getValue().compareTo(item) == 0) {
            		currNode.increaseFound();
            		found = currNode.getFound();
            		break;
            	}
                currNode = currNode.getNext();
            }
            
        	if(found < 1)
        	{
        		mTail.setNext(newNode);
        		//marked out for singly linkedlist
        		//newNode.setPrev(mTail);
        		mTail = newNode;
        	}
        	else
        	{
        		newNode = null;
        	}
        }
        
        mLength++;
	} // end of add()
	
	/**
     * Returns the times that the item value is founded in list.
     * 
     * @param item Value to search for.
     * @return the found variable which present how many times the value is found.
     */
	public int search(T item) {
		// Implement by Tom Yang
		int found = 0;
		Node<T> currNode = mHead;
        while (currNode != null) {
        	if (currNode.getValue().compareTo(item) == 0) {
        		found = currNode.getFound();
        		return found;
        	}
            currNode = currNode.getNext();
        }
		
		// return the found variable
		return found;
	} // end of search()
	
	/**
     * Delete given value from list (delete first instance found).
     *   
     * @param item Value to remove.
     */
	public void removeOne(T item) {
		if(mHead != null)
        {
            Node<T> currNode = mHead;

            // check if value is head node
            // if (currNode.getValue().compareTo(item) == 0) { //double linkedlist
            if (currNode.getValue().compareTo(item) == 0) {
                // check if length of 1
                if (mLength == 1) {
                    mHead = mTail = null;
                }
                else {
                    if(currNode.getFound() == 1)
                    {
                        mHead = currNode.getNext();
                        //marked out for singly linkedlist
                        //mHead.setPrev(null);
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
                Node prevNode = currNode;
                currNode = currNode.getNext();

                while (currNode != null) {
                    if (currNode.getValue().compareTo(item) == 0) { //double linkedlist
                    //if (currNode.getNext().getValue().compareTo(item) == 0) {
                        // check the number 
                        if(currNode.getFound() <= 0)
                            break;
                        
                        if(currNode.getFound() == 1)
                        {
                            //marked out for singly linkedlist
                            //Node prevNode = currNode.getPrev();
                            prevNode.setNext(currNode.getNext());
                            
                            // check if tail
                            if (currNode.getNext() != null) {
                                //marked out for singly linkedlist
                                //currNode.getNext().setPrev(prevNode);
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
                    {
                        //new
                        prevNode = currNode;
                        currNode = currNode.getNext();
                    }
                }   
            }
        }
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement by Tom Yang
        Node<T> currNode = mHead;

        // check if value is head node
        if (currNode.getValue().compareTo(item) == 0) {
            // check if length of 1
            if (mLength == 1) {
                mHead = mTail = null;
                mLength--;
            }
            else {
                int number = currNode.getFound();
                mLength -= number;
                mHead = currNode.getNext();
                //marked out for singly linkedlist
                //mHead.setPrev(null);
                currNode = null;
            }
        }
        // search for value in rest of list
        else {
            Node prevNode = currNode;
            currNode = currNode.getNext();

            while (currNode != null) {
                if (currNode.getValue().compareTo(item) == 0) {
                    int number = currNode.getFound();
                    mLength -= number;
                    //marked out for singly linkedlist
                    //Node prevNode = currNode.getPrev();
                    prevNode.setNext(currNode.getNext());
                    
                    // check if tail
                    if (currNode.getNext() != null) {
                        //marked out for singly linkedlist
                        //currNode.getNext().setPrev(prevNode);
                    }
                    else {
                        mTail = prevNode;
                    }
                    currNode = null;
                }
                else
                    {
                        //new
                        prevNode = currNode;
                        currNode = currNode.getNext();
                    }
            }   
        }
	} // end of removeAll()
	
	/**
     * Print the list in head to tail.
     */
	public void print(PrintStream out) {
		// Implement by Tom Yang
		out.println(toString());
	} // end of print()
	
	/**
     * @return String representation of the list.
     */
    public String toString() {
        Node<T> currNode = mHead;

        StringBuffer str = new StringBuffer();
        int found = 0;

        while (currNode != null) {
        	str.append(currNode.getValue() + printDelim + currNode.getFound() + "\n");
            currNode = currNode.getNext();
        }

        return str.toString();
    } // end of toString();
    
	/**
     * Node type, inner private class.
     */
    private class Node<T>
    {
        /** Stored value of node. */
        private T mValue;
        /** Reference to next node. */
        private Node<T> mNext;
        /** Reference to previous node. */
        //marked out for singly linkedlist
        //private Node<T> mPrev;
        /** Stored value of the same value. */
        private int mNumber;

        public Node(T value) {
            mValue = value;
            mNext = null;
          //marked out for singly linkedlist
            //mPrev = null;
            mNumber = 1;
        }

        public T getValue() {
            return mValue;
        }


        public Node<T> getNext() {
            return mNext;
        }
        
        
        //marked out for singly linkedlist
        //public Node<T> getPrev() {
        //    return mPrev;
        //}
        
        public int getFound() {
        	return mNumber;
        }


        public void setValue(T value) {
            mValue = value;
        }


        public void setNext(Node<T> next) {
            mNext = next;
        }
        
        //marked out for singly linkedlist
        //public void setPrev(Node<T> prev) {
        //    mPrev = prev;
        //}
        
        public void increaseFound() {
        	mNumber++;
        }
        
        public void decreaseFound() {
        	mNumber--;
        }
    } // end of inner class Node
	
} // end of class LinkedListMultiset