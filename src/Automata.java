import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Automata{
    static void  PatternRecognition() {
        //Buffer Reader Class to read input continuously
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        //Variable state simulates the 'q' state variable of DFA
        int state=0;

        System.out.println("Enter the Pattern:\nType '.' to terminate.");
        while(true) {
            try{
                //Reads the input character by character in ascii value
                char ch=(char)reader.read();
                //Terminating condition
                if(ch=='.') {
                    System.out.println("Input Terminated...");
                    break;
                }
                //To skip the newline input
                else if(ch=='\n') {
                    continue;
                }
                //To print OK for every overlapped consecutive appearance of 1
                else if(ch=='1') {
                    System.out.println("OK");
                }
                //Invalid symbols
                else if(ch=='!' || ch=='@' || ch=='$' || ch=='%' || ch=='&') {
                    System.out.println("ERROR in i/p\nExiting...");
                    break;
                }
                else {
                    //Simulating Transition Table
                    switch (state) {
                        case 0: //q0
                            if(ch=='b') {
                                state = 1;
                            }
                            else {
                                state = 0;
                            }
                            break;
                        case 1: //q1
                            if(ch=='b') {
                                state = 2;
                            }
                            else {
                                state = 0;
                            }
                            break;
                        case 2: //q2
                            if(ch=='b') {
                                state = 2;
                            }
                            else if(ch=='#') {
                                state = 3;
                            }
                            else {
                                state = 0;
                            }
                            break;
                        case 3: //q3
                            if(ch=='b') {
                                state = 1;
                            }
                            else if(ch==';') {
                                state = 4;
                                System.out.println("Found SPECIAL");
                            }
                            else {
                                state = 0;
                            }
                            break;
                        case 4: //q4
                            if(ch=='b') {
                                state = 1;
                            }
                            else {
                                state = 0;
                            }
                            break;
                        default:
                            System.out.println("State ERROR!!!");
                    }
                }
            }
            catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        PatternRecognition();
    }
}