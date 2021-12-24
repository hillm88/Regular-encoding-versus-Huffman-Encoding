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


public class QueueNode {
    private char letter;
    private int frequency;
    QueueNode nextNode;
    QueueNode leftNode;
    QueueNode rightNode;
    
    private int length;
    
    public QueueNode(char letter, int frequency){
        this.letter = letter;
        this.frequency = frequency;
        this.nextNode = null;
      
    }

    public int getFrequency(){
        return this.frequency;
    }

    public char getLetter(){
        return this.letter;
    }

    public QueueNode getNextNode(){
        return this.nextNode;
    }

    public QueueNode getNode(){
        return this;
    }


    public void setNextNode(QueueNode next){
        this.nextNode = next;
    }
    
    public void setLeftNode(QueueNode next){
        this.leftNode = next;
    }

    public void setRightNode(QueueNode next){
        this.rightNode = next;
    }

    public QueueNode getLeftNode(){
        return this.leftNode;
    }

    public QueueNode getRightNode(){
        return this.rightNode;
    }

 

    public void setLength(String binaryCode){
        this.length = binaryCode.length();
    }

    public int getLength(){
        return this.length;
    }
    
}
