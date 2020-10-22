package code.example.linklist;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.*;

public class Linklist {

    public static void main(String[] args) throws FileNotFoundException {
        //Create new LinkedList for start
        Linklist originalList = new Linklist();

        //Takes file location from user in example (C:\Users\YourUsername\Desktop\myText.txt) and
        //Read the specified txt document using scanner
        originalList.readFileContent(originalList);

        //Copy the starting LinkedList for our next step which is removing duplicate values
        Linklist uniqueList = originalList.copy();

        //Remove duplicate values
        uniqueList.removeDuplicate();

        //Take user actions 1 for 'Listing', 2 for 'Total word count', 3 for 'Most occurred word' and 0 for 'Exit'
        originalList.userActions(uniqueList, originalList);
    }

    public static final String USER_INPUT_MESSAGE_FOR_ACTIONS = "1-Listing, 2-Total word count, 3-Most occurred word, 0-Exit:";
    Node head; // head of list

    static class Node {
        String data;
        Node next;

        // Constructor
        Node(String d) {
            data = d;
            next = null;
        }
    }

    public static Linklist insert(Linklist list, String data) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            // Insert the new_node at last node
            last.next = new_node;
        }
        return list;
    }

    //This utility method print the LinkedList values distinctly and their occurrence time as original times in reverse order
    //For this method we made two LinkedList which are originalList and uniqueList
    //originalList is same as txt file - we will use it for count times
    //uniqueList contains distinctly separated words from txt file - we will use it for words
    public static void printReverseWithCount(Node head, Linklist list) {
        if (head == null) return;

        // print list of head node
        printReverseWithCount(head.next, list);

        // After everything else is printed
        out.print(head.data + " " + list.eachWordCount(head.data) + ", ");

    }

    //This utility method print total word count on given text file
    public static void printTotalWordCount(Linklist list) {
        int totalWorldCount = list.totalWordCount();
        out.println(totalWorldCount);
    }

    //This utility method print max occurred word on given text file
    public static void printMaxOccurredWord(Linklist list) {
        String maxOccuredWord = "";
        Node currNode = list.head;

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            maxOccuredWord = list.findMaxOccurredWord(list);

            // Go to next node
            currNode = currNode.next;
        }
        out.println(maxOccuredWord + "\n");
    }

    //This utility method calculates how many times each word occurred
    int eachWordCount(String search_for) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (search_for.equals(current.data))
                count++;
            current = current.next;
        }
        return count;
    }

    //This utility method calculates total word count its same with how many nodes are in this LinkedList
    //Because each word is a Node of LinkedList
    int totalWordCount() {
        Node current = head;
        int totalWordCount = 0;
        while (current != null) {
            totalWordCount++;
            current = current.next;
        }
        return totalWordCount;
    }

    //This utility method finds the max occurred word in a LinkedList
    String findMaxOccurredWord(Linklist list) {
        Node p = list.head;

        int maxCount = 0;
        String maxOccurredWord = "";
        while (p != null) {

            // Count all occurrences of p->data
            int count = 1;
            Node q = p.next;
            while (q != null) {
                if (p.data.equals(q.data))
                    count++;
                q = q.next;
            }

            // Update max_count and maxOccurredWord if count
            // is more than max_count
            if (count > maxCount) {
                maxCount = count;
                maxOccurredWord = p.data;
            }

            p = p.next;
        }
        return maxOccurredWord;
    }

    //This utility methods remove all duplicate values in our LinkedList
    public void removeDuplicate() {
        //Node current will point to head
        Node current = head;
        Node index;
        Node temp;

        while (current != null) {
            //Node temp will point to previous node to index.
            temp = current;
            //Index will point to node next to current
            index = current.next;

            while (index != null) {
                //If current node's data is equal to index node's data
                if (current.data.equals(index.data)) {
                    //Here, index node is pointing to the node which is duplicate of current node
                    //Skips the duplicate node by pointing to next node
                    temp.next = index.next;
                } else {
                    //Temp will point to previous node of index.
                    temp = index;
                }
                index = index.next;
            }
            current = current.next;
        }
    }

    //This utility method copy our LinkedList with another reference value so we can
    //manipulate it with removeDuplicate method
    //if we don't use copy method like that they will check from same reference point
    //and our LinkedList will be both changed
    public Linklist copy() {
        Linklist twin = new Linklist();
        Node tmp = head;
        while (tmp != null) {
            insert(twin, tmp.data);
            tmp = tmp.next;
        }
        return twin;
    }

    //This utility method takes file location from user then read text file from specified location
    public void readFileContent(Linklist originalList) throws FileNotFoundException {
        out.println("Please enter your file location(ie: C:\\Users\\YourUsername\\Desktop\\myText.txt): ");
        Scanner fileNameScanner = new Scanner(in);
        String fileName = fileNameScanner.next();

        // pass the fileName to the file as a parameter
        File file = new File(fileName);

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String token = lineScanner.next();
                insert(originalList, token);
            }
            lineScanner.close();
        }
        fileScanner.close();
    }

    //This utility function defines userActions
    public void userActions(Linklist uniqueList, Linklist originalList) {
        Scanner action = new Scanner(in);
        out.println(USER_INPUT_MESSAGE_FOR_ACTIONS);
        int actionNumber = action.nextInt();
        while (actionNumber != 0) {
            if (actionNumber == 1) {
                printReverseWithCount(uniqueList.head, originalList);
                out.println("");
                out.println(USER_INPUT_MESSAGE_FOR_ACTIONS);
                actionNumber = action.nextInt();
            } else if (actionNumber == 2) {
                printTotalWordCount(originalList);
                out.println(USER_INPUT_MESSAGE_FOR_ACTIONS);
                actionNumber = action.nextInt();
            } else if (actionNumber == 3) {
                printMaxOccurredWord(originalList);
                out.println(USER_INPUT_MESSAGE_FOR_ACTIONS);
                actionNumber = action.nextInt();
            } else {
                out.println("Lütfen listenen komutlardan birini giriniz.");
                out.println(USER_INPUT_MESSAGE_FOR_ACTIONS);
                actionNumber = action.nextInt();
            }
        }
    }
}

