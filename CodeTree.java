/**
 * CodeTree.java
 *
 * Uses BinaryTree in order to decode/encode morse
 * code. 
 *
 * @author Jonathon Elfar
 * @author Noah Dietz
 * @version 11/1/2013
 */
import java.lang.IllegalArgumentException;
import java.util.Scanner;
public class CodeTree {
    private static BinaryTree<String> tree;
    private static String[] LUT;

    /**
     * This initializes our lookup table.
     */
    private static void initLUT() {
        LUT = new String[]{
            ".-",
                "-...",
                "-.-.",
                "-..",
                ".",
                "..-.",
                "--.",
                "....",
                "..",
                ".---",
                "-.-",
                ".-..",
                "--",
                "-.",
                "---",
                ".--.",
                "--.-",
                ".-.",
                "...",
                "-",
                "..-",
                "...-",
                ".--",
                "-..-",
                "-.--",
                "--.."
        };
    }

    /**
     * This takes a Morse code String and uses the binary tree
     * to decode it to English.
     */
    public static String decode(String morseText) {
        String output = "";
        String[] splitCodes = morseText.trim().split(" ");

        for(int i = 0; i < splitCodes.length; i++) {
            String curCode = splitCodes[i];
            if(curCode.equals("")) {
                output += " ";
            } else { 
                tree.move(Relative.ROOT);
                for(int x = 0; x < curCode.length(); x++) {
                    char locChar = curCode.charAt(x);
                    if(locChar == '.') { //go left
                        tree.move(Relative.LEFT_CHILD);
                    } else if(locChar == '-') { //go right
                        tree.move(Relative.RIGHT_CHILD);
                    } else {
                        output += locChar;
                    }
                }

                output += tree.retrieve();
            }

        }
        return output.trim();
    }

    /**
     * This takes an English String and uses a lookup table
     * to decode it to Morse.
     */
    public static String encode(String sentence) {
        char[] sentenceArr = sentence.toCharArray();
        String result = "";
        for(int i = 0; i < sentenceArr.length; i++) {
            char curChar;
            if((curChar = Character.toUpperCase(sentenceArr[i])) == 32) {
                result += " ";
            } else if(curChar >= 'A' && curChar <= 'Z'){ 
                int index = curChar - 65;
                if(i == sentenceArr.length - 1)
                    result += LUT[index] + "";
                else
                    result += LUT[index] + " "; 
            }
        }

        return result.trim();
    }

    /**
     * This creates the binary tree.
     */
    public static void createBinaryTree() {
        tree = new BinaryTree<String>();
        tree.insert("root", Relative.ROOT);
        //Left side of tree
        tree.insert("E", Relative.LEFT_CHILD);
        tree.insert("I", Relative.LEFT_CHILD);
        tree.insert("S", Relative.LEFT_CHILD);
        tree.insert("H", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.insert("V", Relative.RIGHT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.insert("U", Relative.RIGHT_CHILD);
        tree.insert("F", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.insert("A", Relative.RIGHT_CHILD);
        tree.insert("R", Relative.LEFT_CHILD);
        tree.insert("L", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.insert("W", Relative.RIGHT_CHILD);
        tree.insert("P", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.insert("J", Relative.RIGHT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        //Right side of tree
        tree.insert("T", Relative.RIGHT_CHILD);
        tree.insert("N", Relative.LEFT_CHILD);
        tree.insert("D", Relative.LEFT_CHILD);
        tree.insert("B", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.insert("X", Relative.RIGHT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.insert("K", Relative.RIGHT_CHILD);
        tree.insert("C", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.insert("Y", Relative.RIGHT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.insert("M", Relative.RIGHT_CHILD);
        tree.insert("G", Relative.LEFT_CHILD);
        tree.insert("Z", Relative.LEFT_CHILD);
        tree.move(Relative.PARENT);
        tree.insert("Q", Relative.RIGHT_CHILD);
        tree.move(Relative.PARENT);
        tree.move(Relative.PARENT);
        tree.insert("O", Relative.RIGHT_CHILD);
    }

    /**
     * Test cases to test morse code.
     */
    public static void testCases() {
        try {
            //Test 1 
            String testStr = "Hello World";
            String expectedOutput = ".... . .-.. .-.. ---  .-- --- .-. .-.. -..";
            System.out.print("Translating '" + testStr + "' to morse... ");
            check(expectedOutput, encode(testStr));

            //Test 2
            testStr = ".... . .-.. .-.. ---  .-- --- .-. .-.. -..";
            expectedOutput = "HELLO WORLD";
            System.out.print("Translating '" + testStr + "' to english... ");
            check(expectedOutput, decode(testStr));

            //Test 3
            testStr = "     .... . .-.. .-.. ---  .-- --- .-. .-.. -..   ";
            expectedOutput = "HELLO WORLD";
            System.out.print("Translating '" + testStr + "' to english... ");
            check(expectedOutput, decode(testStr));
            //System.out.println("\n\n" + decode(testStr) + "\n\n");

            //Test 4
            testStr = "This is a test!";
            expectedOutput = "- .... .. ...  .. ...  .-  - . ... -";
            System.out.print("Translating '" + testStr + "' to morse... ");
            //System.out.println("\n\na" + encode(testStr) + "b\n\n");
            check(expectedOutput, encode(testStr));

            //Test 5
            testStr = "- .... .. ...  .. ...  .-  - . ... -";
            expectedOutput = "THIS IS A TEST";
            System.out.print("Translating '" + testStr + "' to english... ");
            check(expectedOutput, decode(testStr));

            //Test 6
            testStr = ".-.. .. --- -. ...  - .. --. . .-. ...";
            expectedOutput = "LIONS TIGERS";
            System.out.print("Translating '" + testStr + "' to english... ");
            check(expectedOutput, decode(testStr));

            //Test 7
            testStr = "RAT";
            expectedOutput = ".-. .- -";
            System.out.print("Translating '" + testStr + "' to morse... ");
            check(expectedOutput, encode(testStr));
            //System.out.println("\n\na" + encode(testStr) + "b\n\n");
            
            //Test 8
            testStr = ".-. .- -      ";
            expectedOutput = "RAT";
            System.out.print("Translating '" + testStr + "' to english... ");
            check(expectedOutput, decode(testStr));
            
            //Test 9
            testStr = "CoMpUtEr ScIeNcE";
            expectedOutput = "-.-. --- -- .--. ..- - . .-.  " + 
                             "... -.-. .. . -. -.-. .";
            System.out.print("Translating '" + testStr + "' to morse... ");
            check(expectedOutput, encode(testStr));

            //Test 10
            testStr = "-.-. --- -- .--. ..- - . .-.  " + 
                             "... -.-. .. . -. -.-. .";
            expectedOutput = "COMPUTER SCIENCE";
            System.out.print("Translating '" + testStr + "' to english... ");
            check(expectedOutput, decode(testStr));

        } catch (NullPointerException e) {
            System.out.println("Invalid Morse Code");
        }
    }

    /**
     * Helper method for testCases() that compares two strings.
     */
    public static void check(String expectedOutput, String testStr) {
            if(expectedOutput.equals(testStr)){
                System.out.println("Passed!\n");
            } else {
                System.out.println("Failed Test\n");
            }
    }

    public static void main(String[] args) {
        createBinaryTree(); //Creates tree
        initLUT(); //Initializes lookup table
        testCases(); //Runs test cases

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose one of the following operations:");
        System.out.println("- decode Morse to English (enter the letter d)");
        System.out.println("- encode English to Morse (enter the letter e)");
        System.out.println("- print pre-order traversal of Binary Tree (enter the letter p)");
        System.out.println("- Quit (enter the letter q)");
        System.out.println();

        String choice, input;
        while(!(choice = sc.next().substring(0, 1).toLowerCase()).equals("q")) {
            Scanner sc2 = new Scanner(System.in); 

            switch (choice.charAt(0)) {
                case 'd':
                    System.out.print("Enter morse: ");
                    input = sc2.nextLine();
                    System.out.println();
                    try { 
                        System.out.println("Decoded: " + decode(input));
                    } catch (NullPointerException e) {
                        System.out.println("Invalid Morse Code");
                    }
                    break;
                case 'e':
                    System.out.print("Enter English: ");
                    input = sc2.nextLine(); 
                    System.out.println();
                    try { 
                        System.out.println("Encoded: " + encode(input));
                    } catch (NullPointerException e) {
                        System.out.println("Invalid Morse Code");
                    }
                    break;
                case 'p':
                    System.out.println("Pre-order: " + tree.traverse());
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            System.out.println();
            System.out.println("Choose an operation: ");
        }
    }
}
