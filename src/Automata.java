import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Automata {
    static ArrayList<String> TestCases = new ArrayList<>();
    //Variable state simulates the 'q' state variable of DFA
    static int state = 0;

    static void addTestCases() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--Enter the testcase to be added:");
        String str = sc.nextLine();
        TestCases.add(str);
    }
    static void removeTestCases() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--Enter the index of the testcase to be removed:");
        int ind = sc.nextInt();
        if (ind >= 0 && ind < TestCases.size()) {
            TestCases.remove(ind);
        } else {
            System.out.println("  Invalid index");
        }
    }
    static void PatternRecognition(char ch) {
        //To skip the newline input
        if (ch == '\n') return;
            //To print OK for every overlapped consecutive appearance of 1
        else if (ch == '1') {
            System.out.println("  OK");
            return;
        }
        //Invalid symbols
        else if (ch == '!' || ch == '@' || ch == '$' || ch == '%' || ch == '&') {
            System.out.println("--ERROR in i/p (Invalid Character)");
            return;
        }
        else {
            //Simulating Transition Table
            switch (state) {
                case 0: //q0
                    if (ch == 'b') {
                        state = 1;
                    } else {
                        state = 0;
                    }
                    break;
                case 1: //q1
                    if (ch == 'b') {
                        state = 2;
                    } else {
                        state = 0;
                    }
                    break;
                case 2: //q2
                    if (ch == 'b') {
                        state = 2;
                    } else if (ch == '#') {
                        state = 3;
                    } else {
                        state = 0;
                    }
                    break;
                case 3: //q3
                    if (ch == 'b') {
                        state = 1;
                    } else if (ch == ';') {
                        state = 4;
                        System.out.println("--Found SPECIAL");
                    } else {
                        state = 0;
                    }
                    break;
                case 4: //q4
                    if (ch == 'b') {
                        state = 1;
                    } else {
                        state = 0;
                    }
                    break;
                default:
                    System.out.println("--State ERROR!!!");
            }
        }
    }
    static void automate() {
        state=0;
        for (int i = 0; i < TestCases.size();i++) {
            String t1=TestCases.get(i);
            System.out.println("TestCase "+(i+1)+":");
            for(int j=0;j<t1.length();j++) {
                char ch = t1.charAt(j);
                if (ch == '.') {
                    System.out.println("--TestCase Terminated...");
                    break;
                }
                else {
                    PatternRecognition(ch);
                }
            }
            state=0;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("Enter Your Choice: \n1:Input Stream \n2:Automated Testing \n3:Add TestCases \n4:Remove TestCases \n5:View TestCases \n6:Terminate Program");
            String input = sc.nextLine();
            if (input.equals("1")) {
                //Buffer Reader Class to read input continuously
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("--Enter the Pattern:\nType '.' to terminate.");
                while (true) {
                    try {
                        //Reads the input character by character in ascii value
                        char ch = (char) reader.read();
                        //Terminating condition
                        if (ch == '.') {
                            System.out.println("--Input Terminated...");
                            break;
                        }
                        PatternRecognition(ch);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
            else if (input.equals("2")) {
                automate();
            }
            else if(input.equals("3")){
                addTestCases();
            }
            else if(input.equals("4")) {
                removeTestCases();
            }
            else if(input.equals("5")) {
                System.out.println(TestCases);
            }
            else if(input.equals("6")) {
                System.out.println("Terminating Program...");
                System.exit(0);
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }
}