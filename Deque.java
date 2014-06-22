import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node front, end;
    private int size;
    
    // construct an empty deque
    public Deque()
    {
        front = new Node();
        end = new Node();
        front.setNext(end);
        end.setPrev(front);
        size = 0;
    }
    
    // Node Class
    private class Node
    {
        private Node next, prev;
        private Item item;
        
        public void setNext(Node n)
        {
            next = n;
        }
        
        public Node getNext()
        {
            return next;
        }
        
        public void setPrev(Node p)
        {
            prev = p;
        }
        
        public Node getPrev()
        {
            return prev;
        }
        
        public void setItem(Item i)
        {
            item = i;
        }
        
        public Item getItem()
        {
            return item;
        }
    }
    
    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return size;
    }
    
    // insert the item at the front
    public void addFirst(Item item)
    {
        if (item != null)
        {
            size++;
            Node newNode = new Node();
            newNode.setItem(item);
            Node oldFirst = front.getNext();
            newNode.setPrev(front);
            newNode.setNext(oldFirst);
            front.setNext(newNode);
            oldFirst.setPrev(newNode);
        }
        else
        {
            throw new NullPointerException();
        }
    }
    
    // insert the item at the end
    public void addLast(Item item)
    {
        if (item != null)
        {
            size++;
            Node newNode = new Node();
            newNode.setItem(item);
            Node oldLast = end.getPrev();
            newNode.setPrev(oldLast);
            newNode.setNext(end);
            end.setPrev(newNode);
            oldLast.setNext(newNode);
        }
        else
        {
            throw new NullPointerException();
        }
    }
    
    // delete and return the item at the front
    public Item removeFirst()
    {
        if (!isEmpty())
        {
            size--;
            Node oldFirst = front.getNext();
            Node newFirst = oldFirst.getNext();
            front.setNext(newFirst);
            newFirst.setPrev(front);
            Item item = oldFirst.getItem();
            oldFirst = null;
            return item;
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
    
    // delete and return the item at the end
    public Item removeLast()
    {
        if (!isEmpty())
        {
            size--;
            Node oldLast = end.getPrev();
            Node newLast = oldLast.getPrev();
            end.setPrev(newLast);
            newLast.setNext(end);
            Item item = oldLast.getItem();
            oldLast = null;
            return item;
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    // DequeIterator Class
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = front.getNext();
        
        public boolean hasNext()
        {
            return current.getNext() != null;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (hasNext())
            {
                Item item = current.getItem();
                current = current.getNext();
                return item;
            }
            else
            {
                throw new NoSuchElementException();
            }
        }
    }
    
    // unit testing
    public static void main(String[] args)
    {
        
    }
}