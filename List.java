/**
 * The List class implements the linked list using String as the word
 * It provides insertion, deletion, string, copy, merge, get and index methods
 * to process word from the linked list 
 * The class is made of up a list which holds the root node of the linked list
 * an int numNodes which is the number of nodes in the linked list
 * the List class also holds the Node class which is used to implement the linked list
 * Noted Variable names:
 * temp is a temporary String object
 * iterator is a Node used to iterate through the list
 * behind is a node that is always one node behind the iterator
 */

public class List {
	private Node list;
	private int numNodes;

	/**
	 * Constructor sets the list to null to represent and empty list
	 * and sets the numNodes to 0 again an empty list
	 */
	List () 
	{
		list = null;
		numNodes = 0;
	}
	
	/**
	 * Inserts the parameter into the List object so the values in the List object are in 
	 * ascending order by String and then by number  
	 * If the value is already in the array, do not insert it.  
	 * @param inserted_word the Comparable object to insert
	 */
	public void insert (String inserted_word) 
	{
		Node iterator;
		Node behind;
		Node inserted_node = new Node(inserted_word);
		boolean is_inserted = false;
		if (list == null)
		{
			list = inserted_node;
			numNodes++;
		}
		else if (!search(inserted_word)) //if it is already in the list then move on
		{
			iterator = list;
			behind = iterator;
			if (iterator.word.compareToIgnoreCase(inserted_word) > 0)
			{
				inserted_node.next = list;
				list = inserted_node;
				numNodes++;
			}
			else
			{
				while (iterator != null && !is_inserted) //iteratre the list until a position is found or the end
				{
					if (iterator.word.compareToIgnoreCase(inserted_word) > 0)
					{
						behind.next = inserted_node;
						inserted_node.next = iterator;
						numNodes++;
						is_inserted = true; //finish the method word inserted
					}
					behind = iterator;
					iterator = iterator.next;
				}
				if (!is_inserted) //end of list reached and was not inserted
				{
					behind.next = inserted_node;
					numNodes++;
				}
			}
		}
	}
	
	/**
	 * Delete the parameter object if it is in the List object.
	 */
	public void delete (String deleted_word) 
	{
		Node iterator;
		Node behind;
		boolean is_deleted = false;
		if (list == null)
		{
			// list is empty, nothing to delete
		}
		else
		{
			iterator = list;
			behind = list;
			if (iterator.word.compareToIgnoreCase(deleted_word) == 0)
			{
				list = list.next;
				numNodes--;
			}
			else
			{
				while (iterator != null && !is_deleted) //iterates the list until a position is found or the end
				{
					if (iterator.word.compareToIgnoreCase(deleted_word) == 0)
					{
						behind.next = iterator.next;
						numNodes--;
						is_deleted = true;
					}
					behind = iterator;
					iterator = iterator.next;
				}
			}
		}
	}
	
	public void delete (List deleted_List) 
	{
		Node iterator;
		Node behind;
		Node deleted_word;
		boolean is_deleted = false;
		if (list == null || deleted_List == null)
		{
			// list is empty, nothing to delete
		}
		else
		{
			deleted_word = deleted_List.list;
			iterator = list;
			behind = list;
			while (iterator != null && !is_deleted) //iterates the list until a position is found or the end
			{
				if (deleted_word == null)
				{
					is_deleted = true;
				}
				else if (iterator.word.compareToIgnoreCase(deleted_word.word) == 0)
				{
					if (list == iterator)
						list = list.next;
					else
						behind.next = iterator.next;
					deleted_word = deleted_word.next;
					behind = iterator;
					iterator = iterator.next;
					numNodes--;
				}
				else if(iterator.word.compareToIgnoreCase(deleted_word.word) > 0)
				{
					deleted_word = deleted_word.next;
				}
				else
				{
					behind = iterator;
					iterator = iterator.next;
				}
			}
		}
	}
	
	/**
	 * Return the number of Comparable objects are in the List object
	 * @return count of Comparable objects
	 */
	public int count () 
	{
		return numNodes;
	}
	
	/**
	 * Merge the current List object with the parameter List object.  
	 * The results should be a new List object in ascending order with no duplicates.
	 * @param list List object to merge with the current List object
	 * @return new List object with merged values
	 */
	public List merge (List newList) 
	{
		List mergedList = new List();
		Node thisiterator = newList.list;
		Node newiterator = list;
		Node mergediterator = mergedList.list;
		int index = 0;
		while (thisiterator != null && newiterator != null)
		{
			if (thisiterator.word.compareToIgnoreCase(newiterator.word) < 0)
			{
				if (mergediterator == null)
				{
					mergedList.insert(thisiterator.word);
					mergediterator = mergedList.list;
					thisiterator = thisiterator.next;
				}
				else
				{
					mergediterator.next = new Node(thisiterator.word);
					mergediterator = mergediterator.next;
					mergedList.numNodes++;
					thisiterator = thisiterator.next;
				}
			}
			else if (thisiterator.word.compareToIgnoreCase(newiterator.word) > 0)
			{
				if (mergediterator == null)
				{
					mergedList.insert(newiterator.word);
					mergediterator = mergedList.list;
					newiterator = newiterator.next;
				}
				else 
				{
					mergediterator.next = new Node(newiterator.word);
					mergediterator = mergediterator.next;
					mergedList.numNodes++;
					newiterator = newiterator.next;
				}
			}
			else
			{
				thisiterator = thisiterator.next;
			}
		}
		while (thisiterator != null)
		{
			mergediterator.next = new Node(thisiterator.word);
			mergediterator = mergediterator.next;
			mergedList.numNodes++;
			thisiterator = thisiterator.next;			
		}
		while (newiterator != null)
		{
			mergediterator.next = new Node(newiterator.word);
			mergediterator = mergediterator.next;
			mergedList.numNodes++;
			newiterator = newiterator.next;
		}

		mergedList.numNodes = numNodes + newList.numNodes;
		return mergedList;	
	} 
	//creates a new node with the word of the non-empty node

	/**
	 * Search will search for the list for the given word a
	 */
	public boolean search (String searching) 
	{
		Node iterator = list;
		int pos = 0;
		boolean found = false;
		while (pos < numNodes && !found)
		{
			if (iterator.word.compareToIgnoreCase(searching) == 0)
			{
				found = true;
			}
			else
			{
				pos ++;
				iterator = iterator.next;
			}
		}
		return found;
	}
	
	/**
	 * Copies the contents of the collection List object to an array.  The array contains the 
	 * same number of elements as the original collection and objects are in the same order.
	 * @return String of the elements in the list
	 */
	 public String toString() 
	 {
		String outputString = "";
		Node iterator = list;
		while (iterator != null)
		{
			outputString = outputString + iterator.word;
			if (iterator.next != null)
				outputString = outputString + "\n";
			iterator = iterator.next;
		}
		return outputString;
	 }
	 
	 /**
	  * The Node class creates the nodes that will make up the linked list
	  * The word word will store the int and String to implement the linked list
	  */
	 private class Node
	 {
		 private String word;
		 private Node next = null;
		 Node(String new_word)
		 {
			 word = new_word;
		 }
	 }
}