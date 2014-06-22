import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] queue;
    private int N;
    
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        queue = (Item[]) new Object[1];
        N = 0;
    }
    
    // is the queue empty?
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size()
    {
        return N;
    }
    
    // add the item
    public void enqueue(Item item)
    {
        if (item != null)
        {
            if (queue.length == N)
            {
                resize(2 * queue.length);
            }
            queue[N++] = item;
        }
        else
        {
            throw new NullPointerException();
        }
    }
    
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = queue[i];
        }
        queue = copy;
        copy = null;
    }
    
    // delete and return a random item
    public Item dequeue()
    {
        if (N > 0)
        {
            int rand = StdRandom.uniform(N--);
            Item item = queue[rand];
            queue[rand] = queue[N];
            queue[N] = null;
            if (N > 0 && N == queue.length / 4)
            {
                resize(queue.length / 2);
            }
            return item;
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
    
    // return (but do not delete) a random item
    public Item sample()
    {
        if (N > 0)
        {
            return queue[StdRandom.uniform(N)];
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int i = N;
        private boolean random = false;
        
        public boolean hasNext()
        {
            return i > 0;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (!random)
            {
                StdRandom.shuffle(queue, 0, i - 1);
                random = true;
            }
            if (hasNext())
            {
                return queue[--i];
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