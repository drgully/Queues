public class Subset
{
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue rq = new RandomizedQueue();
        String input;
        while (!StdIn.isEmpty())
        {
            input = StdIn.readString();
            rq.enqueue(input);
        }
        for (int i = 0; i < k; i++)
        {
            System.out.println(rq.dequeue());
        }
    }
}