import java.io.*;
import java.util.*;

class Sender  
{
    public synchronized static void Deposit() 
    {
        Random rand = new Random();
        int branch = rand.nextInt(10);
        int index = rand.nextInt(100);
        int amount = rand.nextInt(5000);
        int k=-1;   
        First.Node head_ref = First.arr[branch];
        while(k!=index)
        {
            k++;
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref=head_ref.next;
        }
        head_ref.money = head_ref.money + amount;
        //System.out.println("*********\n");
    }
    public synchronized static void Withdrawal() 
    {
        Random rand = new Random();
        int branch = rand.nextInt(10);
        int index = rand.nextInt(100);
        int amount = rand.nextInt(5000);
        int k=-1;   
        First.Node head_ref = First.arr[branch];
        while(k!=index)
        {
            k++;
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref=head_ref.next;
        }
        head_ref.money = head_ref.money - amount;
        if(head_ref.money<0)
        {
            head_ref.money = head_ref.money + amount;
        }
        //System.out.println("*********\n");
    }
    public synchronized static void Transfer() 
    {
        Random rand = new Random();
        int branch_from= rand.nextInt(10);
        int branch_to= rand.nextInt(10);
        int index_from = rand.nextInt(100);
        int index_to = rand.nextInt(100);
        int amount = rand.nextInt(1000);
        int k1=-1;
        int k2=-1;   
        First.Node head_ref1 = First.arr[branch_from];
        First.Node head_ref2 = First.arr[branch_to];
        while(k1!=index_from)
        {
            k1++;
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref1=head_ref1.next;
        }
        while(k2!=index_to)
        {
            k2++;
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref2=head_ref2.next;
        }
        if(head_ref1.money>=amount)
        {
            head_ref1.money=head_ref1.money-amount;
            head_ref2.money=head_ref2.money+amount;
        }
        //System.out.println("*********\n");
    }
    public synchronized static void Add()
    {
        Random rand = new Random();
        int branch = rand.nextInt(10);
        int amount = rand.nextInt(5000);
        String temp = "";
        temp = temp + (branch-48 + '0');
        for(int k=0;k<9;k++)
        {
            int temp1=rand.nextInt(10);
            temp = temp + (temp1-48 + '0');
        }
        First.push(First.arr[branch],temp,amount,branch);
    }
    public synchronized static void Delete()
    {
        Random rand = new Random();
        int branch = rand.nextInt(10);
        int index = rand.nextInt(100);
        First.Node head_ref = First.arr[branch];
        int k=-1;
        while(k!=index)
        {
            k++;
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref=head_ref.next;
        }
        First.Node temp = head_ref.next.next;
        head_ref.next=temp;
    }
    public synchronized static void Transfer_Account()
    {
        Random rand = new Random();
        int branch_from = rand.nextInt(10);
        int branch_to = rand.nextInt(10);
        int index = rand.nextInt(100);
        First.Node head_ref = First.arr[branch_from];
        int k=-1;
        while(k!=index)
        {
            k++;
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref=head_ref.next;
        }
        First.push(First.arr[branch_to],head_ref.account_number,head_ref.money,branch_to);
        First.Node temp = head_ref.next.next;
        head_ref.next=temp;
    }
}

class MultithreadingDemo extends Thread 
{
    int b;
    public MultithreadingDemo()
    {
        //b=index;
    }

    @Override
    public void run() 
    {
        int[] queries;
        int q = 10000;
        queries = new int[q];
        for(int i=0;i<3300;i++)
        {
            queries[i]=1;
            Sender.Deposit();
        }
        for(int i=3300;i<6600;i++)
        {
            queries[i]=2;
            Sender.Withdrawal();
        }
        for(int i=6600;i<9900;i++)
        {
            queries[i]=3;
            Sender.Transfer();
        }
        for(int i=9900;i<9930;i++)
        {
            queries[i]=4;
            Sender.Add();
        }
        for(int i=9930;i<9960;i++)
        {
            queries[i]=5;
            Sender.Delete();
        }
        for(int i=9960;i<10000;i++)
        {
            queries[i]=6;
            Sender.Transfer_Account();
        }
    }
} 
   

public class First 
{
    public static class Node 
    {
        String account_number;
        long money;
        Node next;
    };
    public static Node[] arr;
    public static int q;
    public static int count=0;
    public static Node push(Node head_ref, String a, long b,int index)
    {
        Node new_node = new Node();
        new_node.account_number = a;
        new_node.money = b;
        new_node.next = head_ref;
        head_ref = new_node;
        return arr[index] = head_ref;
    }
    public static void printList(Node head_ref,int i)
    {
        //System.out.printf("Branch %d\n",i);    
        while(head_ref!=null)
        {
            //System.out.printf("%s %d\n", head_ref.account_number,head_ref.money);
            head_ref=head_ref.next;
        }
        //System.out.println("*********\n");
    }
    public static void main(String[] args) 
    {
        long start_time = System.currentTimeMillis();
        arr = new Node[10];
        Random rand = new Random();
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10000;j++)
            {
                String temp = "";
                long pow10=1;
                int index = i;
                temp = temp + (index-48 + '0');
                for(int k=0;k<9;k++)
                {
                    int temp1=rand.nextInt(10);
                    temp = temp + (temp1-48 + '0');
                }
                long temp2 = rand.nextInt(10000);
                push(arr[index],temp,temp2,index);
            }
        }
        for(int i=0;i<10;i++)
        {
            printList(arr[i],i);
        }
        //Scanner sc= new Scanner(System.in);
        //int a= sc.nextInt();
        String s = "";
        //System.out.println("****INITIAL INFORMATION****" + s);
        //System.out.println("Enter number of queries" + s);
        //System.out.println("Enter 1 for cash deposit." + s);
        //System.out.println("Enter 2 for cash withdrawal." + s);
        //System.out.println("Enter 3 for money transfer." + s);
        //System.out.println("Enter 4 for add customer account." + s);
        //System.out.println("Enter 5 for delete customer account." + s);
        //System.out.println("Enter 6 for transfer customer account." + s);
        //System.out.printf("Number of queries are %d\n", q);
        try
        {
            Thread[] object;
            object = new Thread[100];
            for (int i=0; i<100; i++) 
            {
                object[i] = new Thread(new MultithreadingDemo());
                object[i].start();
            }
            for(int i=0;i<100;i++)
            {
                object[i].join();
            }
        }
        catch(Exception e)
        {
            System.out.println("Interrupted");
        }
        long finish_time = System.currentTimeMillis();
        finish_time = finish_time - start_time;
        String ans = "Execution timecin milliseconds is : ";
        System.out.printf("%s ",ans);
        System.out.println(finish_time);
    }
} 
