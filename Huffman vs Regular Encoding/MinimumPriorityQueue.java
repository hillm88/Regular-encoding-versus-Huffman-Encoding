import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;
import java.util.*;
import java.io.*;
import javax.print.event.PrintEvent;
import javax.security.auth.login.CredentialException;
import javax.sound.midi.SysexMessage;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Comparator;



public class MinimumPriorityQueue {
    
    
    

    //The total storage of the huffman code
    public static int totalStorage=0;

    
 
    //Head of the queue
    public static QueueNode headOfQueue  = null;

    //Default constructor for the priority queue
    public MinimumPriorityQueue(){
        //Initalizing the letter array
       
        System.out.println("Nothing in the queue");
    }

    //Putting stuff in the queue
    public static QueueNode enQueue(char letter,int frequncy){
        
        //Creating a new queue node
        QueueNode currentQueue = new QueueNode(letter, frequncy);
        
        //Setting the head of the queue
        QueueNode newHeadNode = headOfQueue;

        //If the head == null, set it to the current queue
        if(headOfQueue==null){

            headOfQueue = currentQueue;
        }

        //Checking that the head of the queue has a greater frequency than the current queue node
        else if(headOfQueue.getFrequency() > currentQueue.getFrequency()){
            //If it does, set the next node to the head of the queue
            currentQueue.setNextNode(headOfQueue);
            //Set the head of the queue to the current node
            headOfQueue = currentQueue;
        }
        //If the frequency of two nodes are the same
        else if(headOfQueue.getFrequency()==currentQueue.getFrequency()){
            //Set the next node to be the head of queue and set the head of the queue to be the current node
            currentQueue.setNextNode(headOfQueue);
            headOfQueue=currentQueue;
        }

        //While the headnode is null
        else{
            while(newHeadNode!=null){
                //If the current queue frequency is greater than the head node frequency and the node after the head node is null
                if((currentQueue.getFrequency()>newHeadNode.getFrequency())&&newHeadNode.getNextNode()==null){
                    //Link the newheadNode and the current queue
                    newHeadNode.setNextNode(currentQueue);
                }
                //If the current nodes frequncy is greater than the headnodes, but less than the node after the head node
                else if((currentQueue.getFrequency() > newHeadNode.getFrequency()) && (currentQueue.getFrequency()<newHeadNode.getNextNode().getFrequency())){
                    
                    currentQueue.setNextNode(newHeadNode.getNextNode());
                    newHeadNode.setNextNode(currentQueue);
                }
                newHeadNode = newHeadNode.getNextNode();
            }

        }

        return currentQueue;


    }




    //Dequeueing a node
    public static QueueNode deQueue(){
        //making a temporary head node
        QueueNode temporaryHeadNode = headOfQueue;
        //The head of the queue is now equal to the next in line node
        headOfQueue = headOfQueue.getNextNode();
        return temporaryHeadNode;
    }

    public static void print(QueueNode headNode){
        QueueNode temporaryHeadNode = headNode;
        while(temporaryHeadNode != null){
            System.out.print(temporaryHeadNode.getLetter() + "( " + temporaryHeadNode.getFrequency() + " )" + "->");
            temporaryHeadNode=temporaryHeadNode.getNextNode();
        }

    }

    //Method to dequeue nodes and print out the statements to go along with it
    public static QueueNode deQueuer(){
        QueueNode deQueueNode = deQueue();
        System.out.println(" ");
        System.out.println("Removing  " + deQueueNode.getLetter() + " "+ deQueueNode.getFrequency());
        return deQueueNode;
    }

    //Method used to dequeue nodes and enqueue combined nodes
    public static void queueNodeAdder(){

        //Dequeueing 2 nodes
        QueueNode deQueueNode = deQueuer();
        QueueNode deQueueNode2 = deQueuer();
      
        
       
        //Enqueueing a node that is a combination of 2 nodes frequencys
        QueueNode newNode = enQueue('N',deQueueNode2.getFrequency()+deQueueNode.getFrequency());
        
        //Giving this new node left and right children
        newNode.setLeftNode(deQueueNode);
        newNode.setRightNode(deQueueNode2);

   
        System.out.println(newNode.getFrequency());

        print(headOfQueue);

        

    }






    //A function that prints the huffman code and calculates how much storage it will take.
    public static void printHuffmanTree(QueueNode head, String HuffmanCode)
    {
  
      
        //If the left and right node are null, it is a leaf and therefor a letter. 
        if (head.getLeftNode()== null && head.getRightNode()== null && Character.isLetter(head.getLetter())) {
  
            //Setting the length of each nodes huffman code
            head.setLength(HuffmanCode);
            
            //Print out each letter and its respective huffman code
            System.out.println(head.getLetter() + ":" + HuffmanCode);
            
            //Calculating the storage by taking the length of each code and multiplying it by how often it appears.
            totalStorage = totalStorage + (HuffmanCode.length()*head.getFrequency());
            return;
        }
       
 
        //this recursively calls for the code to go in order and traverse the whole tree, any time it goes left it adds a zero and any time it 
        //goes right it adds a 1.
        printHuffmanTree(head.getLeftNode(), HuffmanCode + "0");
      
        printHuffmanTree(head.getRightNode(), HuffmanCode + "1");
    }






    public static void main(String[] args){
       


        try
		{
            int[] array = new int[26];
			URL server = new URL("https://en.wikipedia.org/wiki/AMD_580_chipset_series");
			HttpURLConnection webpageConnection= (HttpURLConnection) server.openConnection();
			webpageConnection.setRequestMethod("GET");
			webpageConnection.connect();
			InputStream in = webpageConnection.getInputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = in.read(buffer);

			while(bytesRead > 0)
			{
				for(int i=0; i<bytesRead; i++){
				
                    //If the buffer position is a letter
                    if( buffer[i] >= 'A' && buffer[i] <= 'Z' || buffer[i] >= 'a' && buffer[i] <= 'z'){
                        //If the buffer position is a lowercase letter
                        if(buffer[i] >= 'a'){
                            //Turning the letter into an array position for our letter count array
                            array[buffer[i]-'a']++ ;
                        }
                        //If the buffer position is a uppercase letter
                        else{
                            //Turning the letter into an array position for our letter count array
                            array[buffer[i]-'A']++ ;
                        }
                        
                    }
                
                }
					
				bytesRead = in.read(buffer);
			}


            
         
            //Printing the count of each letter
            for (int j = 0; j<26;j++){

                //Enqueueing each letter as the looping variable + 97 to get to the lower case ascii values and using the array which contains the frequencies.
                enQueue( (char)(j+ 97),array[j]);
                //Printing each letter and its frequency.
                System.out.println("Letter " + (char)(j+ 97) + " "+ array[j]);
				
                
      
                
            }

            //Calling the node adder to combine all the nodes into a tree
            for(int i = 0;i<25;i++){
                queueNodeAdder();
            }



			in.close();
        }

        catch(Exception e) { System.out.println("Error opening page\n"+e); }


      



   

        System.out.println(" ");
    

        //Printing the huffman Tree
        printHuffmanTree(headOfQueue,"");

        //Printing the total storage
        System.out.println(totalStorage);


    }

    public static int totalStorageHuffman(){
        return totalStorage;
    }


}


        

