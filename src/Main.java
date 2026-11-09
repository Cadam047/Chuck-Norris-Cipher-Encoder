import java.util.*;
import java.util.stream.Collectors;

public class Main {
     static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input operation (encode/decode/exit):");
        String input = scanner.nextLine();


        // We need to create a while loop to repeat,
        // the same input of encode, decode and exit

        // Let's divide all these operations

        // This is the beginning of the while loop
        while (true) {

            // encode
            if (input.equals("encode")) {

                System.out.println("Input string:");
                String encodeInput = scanner.nextLine();

                char[] newInputChar = encodeInput.toCharArray();

                // We need to find a way to make the chars
                // into decimals

                // We need to make a for loop to put inside a new
                // char array, the numbers
                int[] newInputCharInt = new int[newInputChar.length];

                for (int i = 0; i < newInputChar.length; i++) {

                    newInputCharInt[i] = (int) newInputChar[i];

                }

                // First we need to separate the 1s from the 0s

                // Like this is the entire number System.out.println(Integer.toBinaryString(newInputCharInt[i]));

                // not separated from the 1s or 0s

                String[] binaryNumbersSeparated = new String[newInputChar.length];

                for (int i = 0; i < binaryNumbersSeparated.length; i++){

                    binaryNumbersSeparated[i] = String.format("%7s", Integer.toBinaryString(newInputCharInt[i])).replace(' ', '0');

                }

                String binaryNumberMix = String.join("", binaryNumbersSeparated);


                // Now I need to grab one world, and explore it

                char[] partOneCharArray = binaryNumberMix.toCharArray();

                //Let's print the Encoded String

                System.out.println("Encoded string:");

                if (encodeInput.isEmpty()){
                    break;
                }

                for (int j = 0; j < partOneCharArray.length;){

                    int count = 0;

                    // In here we are saying that the first block is 0
                    if (partOneCharArray[j] == '0'){

                        System.out.print("00");

                        while (partOneCharArray[j] == '0') {

                            count++;
                            j++;

                            if (j == partOneCharArray.length) {
                                break;
                            }


                        }


                        System.out.print(" ");

                        for (int k = 0; k < count; k++){
                            System.out.print("0");
                        }

                        System.out.print(" ");

                    }

                    else if (partOneCharArray[j] == '1') {

                        System.out.print("0");

                        while (partOneCharArray[j] == '1') {

                            count++;
                            j++;

                            if (j == partOneCharArray.length) {
                                break;
                            }

                        }

                        System.out.print(" ");

                        for (int k = 0; k < count; k++){
                            System.out.print("0");
                        }
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }


            // decode
            else if (input.equals("decode")) {

                try {

                    System.out.println("Input encoded string:");
                    String decodeInput = scanner.nextLine();
                    String[] newArray = decodeInput.split(" ");
                    String[] newArray2 = new String[newArray.length];

                    //We use this for loop to fill out the array with binary numbers
                    for (int i = 0; i < newArray.length; i++) {

                        // Even
                        if (i % 2 == 0) {

                            if (newArray[i].equals("0")) {
                                newArray2[i] = "1";
                            }

                            if (newArray[i].equals("00")) {
                                newArray2[i] = "0";
                            }

                        }

                        //Odd
                        if (i % 2 == 1) {
                            newArray2[i] = String.valueOf(newArray[i].length());
                        }

                    }

                    // Now the array is more clear with newArray2
                    //System.out.println(Arrays.toString(newArray2));

                    int[] newArray3 = Arrays.stream(newArray2)
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    int copyI = 0;
                    int copyJ = 1;


                    //Let's find the length of the binary list

                    int lengthOfBinary = 0;

                    for (int i = 0; i < newArray3.length; i++) {

                        if (i + 1 < newArray3.length) {
                            lengthOfBinary += newArray3[i + 1];
                        }

                        else {
                            break;
                        }

                    }


                    int insideArray4 = 0;


                    for (; copyI < newArray3.length; ) {

                        int copyK = 0;

                        for (; copyK < newArray3[copyJ]; copyK++) {
                            insideArray4++;
                        }

                        copyI += 2;
                        copyJ += 2;

                    }

                    int[] newArray4 = new int[insideArray4];

                    int copyI2 = 0;
                    int copyJ2 = 1;
                    int insideArray4Second = 0;

                    for (; copyI2 < newArray3.length; ) {

                        int copyK = 0;

                        for (; copyK < newArray3[copyJ2]; copyK++) {
                            newArray4[insideArray4Second] = newArray3[copyI2];
                            insideArray4Second++;
                        }

                        copyI2 += 2;
                        copyJ2 += 2;

                    }

                    // Let's find the length
                    int numberToDivide = newArray4.length / 7;

                    int numberToDivide2 = newArray4.length / numberToDivide;

                    String[] newArray6 = new String[numberToDivide];

                    int outI = 0;
                    int newArray6I = 0;
                    for (int j = 0; j < numberToDivide; j++) {

                        int[] numberToPut = new int[7];

                        for (int i = 0; i < numberToDivide2; i++) {
                            numberToPut[i] = newArray4[outI];
                            outI++;
                        }

                        String numberToPutString = Arrays.stream(numberToPut)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining(""));

                        newArray6[newArray6I] = numberToPutString;
                        newArray6I++;

                    }

                    // Let's transform this array of Strings into parseInt
                    Integer[] helloMaster = new Integer[numberToDivide];

                    for (int i = 0; i < numberToDivide; i++) {
                        helloMaster[i] = Integer.parseInt(newArray6[i], 2);
                    }

                    // Now we transform the decimals into chars
                    char[] message = new char[numberToDivide];

                    for (int i = 0; i < numberToDivide; i++) {
                        message[i] = (char) (int) helloMaster[i];
                    }


                    // Let's print out the message
                    System.out.println("Decoded string:");

                    for (int i = 0; i < numberToDivide; i++) {
                        System.out.print(message[i]);
                    }
                    System.out.println();
                }

                catch (ArithmeticException e) {
                    System.out.println("Encoded string is not valid");
                }

                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Encoded string is not valid");
                }

                catch (NumberFormatException e) {
                    System.out.println("Encoded string is not valid");
                }


            }

            // exit

            else if (input.equals("exit")) {
                System.out.println("Bye!");
                break;
            }


            else {
                System.out.println("There is no " + "'" + input + "'" + " operation");
            }

            System.out.println("Please input operation (encode/decode/exit):");
            input = scanner.nextLine();
        }
    }
}
