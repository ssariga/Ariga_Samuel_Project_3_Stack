import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * StackSort is a program that will use two stacks to sort an array of integer values.
 * 
 * @author Charles Hoot
 * @version 5.0
 */
public class StackSort {

    public static void main(String args[]) {

        int data[] = null;
        int result[] = null;



        Scanner input;
        input = new Scanner(System.in);

        System.out.println("This program sorts an array of integer values.");


        // Create an empty array of integers
        data = createArray(0, 1, 1);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with one integer
        data = createArray(1, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with two integers
        data = createArray(2, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 10 integers
        data = createArray(10, 0, 9999);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 20 integers
        data = createArray(20, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        System.out.println("Please enter the number of values to sort");
        int size = getInt("   It should be an integer value greater than or equal to 1.");
        // Create an array of the given size

        data = createArray(size, 0, 99);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();


    }


    /**
     * Use two stacks to sort the data in an array
     *
     * @param data An array of integer values to be sorted.
     * @return     An array of sorted integers. 
     */
    private static int[] doStackSort(int data[]) {

    int result[] = new int[data.length];

        //creation of two stacks
        StackInterface<Integer> lowerValues = new VectorStack<>(); //step 2
        StackInterface<Integer> upperValues = new VectorStack<>(); // step3

        //if the array is already sorted
        if (data == null || data.length <= 1)
        {
            return data;
        }

        //creation of pivot called temp. Allows for redirection of memory from one location to another.
        //I.E moving data from left to right, or right to left.

        for (int i = 0; i < data.length; i++)
        {
            int temp = data[i];

            while(!lowerValues.isEmpty() && lowerValues.peek() > temp)
            {
                int move = lowerValues.pop();
                upperValues.push(move);
            }

            while(!upperValues.isEmpty() && upperValues.peek() < temp)
            {
                int move = upperValues.pop();
                lowerValues.push(move);
            }

            upperValues.push(temp);

        }



        while(!lowerValues.isEmpty())
        {
            int move = lowerValues.pop();
            upperValues.push(move);

        }


        for(int i = 0; i < data.length; i++)
        {
            int values = upperValues.pop();
            result[i] = values;
        }

        //error checking
        System.out.println("Merged Result: " + Arrays.toString(result));
    // ADD CODE HERE TO SORT THE ARRAY USING TWO STACKS

        //sorts properly, but data is not transferred. ask for help

        return result;

    }

    /**
     * Use a stack and get its size
     *
     * @param stack size to coverted to array size.
     * @return     the size of the stack.
     */
    private static int countStackSize(VectorStack<Integer> stack)
    {
        int size = 0;

        while (!stack.isEmpty())
        {
            stack.pop();
            size++;
        }

        return size;
    }

    /**
     * Load an array with data values
     *
     * @param size The number of data values to generate and place in the array.
     * @param min The minimum value to generate.
     * @param max The maximum value to generate.
     * @return     An array of randomly generated integers. 
     */
    private static int[] createArray(int size, int min, int max) {

        Random generator = new Random();

        // If we get a negative size, just make the size 1
        if (size < 0) {
            size = 1;
        }
        // We need max > min for the random number generator to be happy

        if (max <= min) {
            max = min + 1;
        }

        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = min + generator.nextInt(max - min);
        }

        return data;
    }

    /**
     * Create a string with the data values from an array
     *
     * @param data An array of integer values.
     * @return A string representation of the array.
     */
    private static String representationOfArray(int data[]) {
        String result = new String("< ");
        for (int i = 0; i < data.length; i++) {
            result += data[i] + " ";
        }
        result += ">";

        return result;
    }

    /**
     * Get an integer value
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt) {
        Scanner input;
        int result = 10;        //default value is 10
        try {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        } catch (NumberFormatException e) {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;

    }
}
