import java.util.Iterator;
import java.lang.*;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A generic doubly linked list implementation
 * 
 * @param <T> the type of elements stored in the list
 */
public class GenericLinkedList<T> implements Iterable<T>{
	private Node first; 
	private Node last; 
	private int size;

	/**
	 * Constructs an empty GenericLinkedList.
	 */
	public GenericLinkedList() {
	    first = null;
	    last = null;
	    size = 0;
	}
	
	/**
	 * Checks if the list contains the given element
	 *
	 * @param element the element to search for
	 * @return true if the element exists in the list, false otherwise
	 */
	public boolean contains(T element) {
		GenericIterator iterate = new GenericIterator();
		while(iterate.hasNext()) {
			T temp = iterate.next();
			if(temp == element) {
				return true;
			}
			if(temp!=null&&temp.equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns the element at the specified index.
	 *
	 * @param index the position of the element
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public T get(int index) {
		T temp = null;
		GenericIterator iterate = new GenericIterator();
		if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Given index is out of bounds");
	    }
		for(int i =0;i<=index;i++) {
			if(iterate.hasNext()) {
				temp = iterate.next();
			}
		}
		return temp;
	}

	/**
	 * Returns the first element in the list
	 *
	 * @return the first element
	 */
	public T getFirst() {
		return first.item;
	}

	/**
	 * Returns the last element in the list
	 *
	 * @return the last element
	 */
	public T getLast() {
		return last.item;
	}

	/**
	 * Checks whether the list is empty.
	 *
	 * @return true if the list contains no elements
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a list iterator over the elements in this list.
	 *
	 * @return a ListIterator for the list
	 */
	public ListIterator<T> iterator(){
		GenericIterator iterate = new GenericIterator();
		return iterate;
	}
	
	
	/**
	 * Removes and returns the element at the specified index.
	 *
	 * @param index the index of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public T remove(int index) {
		GenericIterator iterate = new GenericIterator();
		if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Given index is out of bounds");
	    }
		for(int i = 0;i<index;i++) {
			iterate.next();
		}
		T temp = iterate.next();
		iterate.remove();
		return temp;
	}

	/**
	 * Removes the first occurrence of the specified element
	 *
	 * @param element the element to remove
	 * @return true if the element was removed
	 */
	public boolean remove(T element) {
		GenericIterator iterate = new GenericIterator();
		for(int i = 0;i<size;i++) {
			T temp = iterate.next();
			if(temp.equals(element)) {
				iterate.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes and returns the first element of the list.
	 *
	 * @return the removed element
	 * @throws NoSuchElementException if the list is empty
	 */
	public T removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException("List is empty");
		}
		T temp = first.item;
		if(size == 1) {
			first = null;
			last = null;
		}
		else {
			first = first.next;
			first.prev = null;
		}
		size--;
		return temp;
	}

	/**
	 * Removes and returns the last element of the list
	 *
	 * @return the removed element
	 * @throws NoSuchElementException if the list is empty
	 */
	public T removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException("List is empty");
		}
		T temp = last.item;
		if(size == 1) {
			first = null;
			last = null;
		}
		else {
			last = last.prev;
			last.next = null;
		}
		size--;
		return temp;
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Converts the list to an array.
	 *
	 * @return an array containing all elements in the list
	 */
	public Object[] toArray() {
		Object[] temp = new Object[size];
		GenericIterator iterate = new GenericIterator();
		for(int i =0;i<size;i++) {
			temp[i] = iterate.next();
		}
		return temp;
	}
	
	/**
	 * An iterator implementation for the GenericLinkedList.
	 */
	private class GenericIterator implements ListIterator<T>{
		Node tracker = first;
		Node lastSent;

		/**
		 * Checks if there is a next element.
		 */
		@Override
		public boolean hasNext() {
			return (tracker != null);
		}

		/**
		 * Returns the next element in the list
		 *
		 * @throws NoSuchElementException if no next element exists
		 */
		@Override
		public T next() {
			if (!hasNext()) {
		        throw new NoSuchElementException("The list ends here");
		    }
				T i = tracker.item;
				lastSent = tracker;
				tracker = tracker.next;
				return i;
		}

		/**
		 * Checks if there is a previous element.
		 */
		@Override
		public boolean hasPrevious() {
			if (tracker == null) {
		        return last != null;
		    }
			return (tracker.prev!=null);
		}

		/**
		 * Returns the previous element in the list.
		 *
		 * @throws NoSuchElementException if no previous element exists
		 */
		@Override
		public T previous() {
			if (!hasPrevious()) {
		        throw new NoSuchElementException("The list ends here");
		    }
			if(tracker == null) {
				tracker = last;
			}
			else {
				tracker = tracker.prev;
			}
			lastSent = tracker;
			return tracker.item;
		}

		/**
		 * Unsupported operation.
		 */
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException("Unsupported function"); 
		}

		/**
		 * Unsupported operation.
		 */
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException("Unsupported function"); 
		}

		/**
		 * Removes the last element returned by the iterator.
		 *
		 * @throws IllegalStateException if next() or previous() has not been called
		 */
		@Override
		public void remove() {
			if (lastSent == null) {
		        throw new IllegalStateException("Must call a different node first");
		    }
			if(lastSent.prev == null) {
				first = first.next;
			}
			else {
				lastSent.prev.next = lastSent.next;
			}
			if(lastSent.next == null) {
				last = last.prev;
			}
			else {
				lastSent.next.prev = lastSent.prev;
			}
			if(tracker == lastSent) {
				tracker = lastSent.next;
			}
			size--;
			lastSent = null;
		}

		/**
		 * Unsupported operation.
		 */
		@Override
		public void set(Object e) {
			throw new UnsupportedOperationException("Unsupported function"); 
		}

		/**
		 * Unsupported operation.
		 */
		@Override
		public void add(Object e) {
			throw new UnsupportedOperationException("Unsupported function"); 
		}
		
	}

	/**
	 * Node class representing an element in the linked list.
	 */
	private class Node {
        T item;
        Node next;
        Node prev;

        /**
         * Creates a new node containing the given item.
         *
         * @param item the value stored in the node
         */
        Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

	/**
	 * Adds an element to the beginning of the list.
	 *
	 * @param item the element to add
	 */
	public void addFirst(T item) {
		Node temp = new Node(item);
		if(isEmpty()) {
			first = temp;
			last = temp;
		}
		else {
			temp.next = first;
			first.prev = temp;
			first = temp;
		}
		 size++;
	}

	/**
	 * Adds an element to the end of the list.
	 *
	 * @param item the element to add
	 */
	public void addLast(T item) {
		Node temp = new Node(item);
		if(isEmpty()) {
			first = temp;
			last = temp;
		}
		else {
			last.next = temp;
			temp.prev = last;
			last = temp;
		}
		 size++;
	}

	/**
	 * Removes all elements from the list.
	 */
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}
}