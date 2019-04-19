import java.util.ArrayList;

public class Tools {
    public static ArrayList<String> SwapInStringArrayList (Integer a, Integer b, ArrayList<String> StringArray) {

        String temp = StringArray.get(a);

        StringArray.set(a, StringArray.get(b));

        StringArray.set(b, temp);

        return StringArray;
    }

    public static ArrayList<Integer> SwapInIntegerArrayList (Integer a, Integer b, ArrayList<Integer> IntegerArray) {

        Integer temp = IntegerArray.get(a);

        IntegerArray.set(a, IntegerArray.get(b));

        IntegerArray.set(b, temp);

        return IntegerArray;
    }

    public static double[] SwapIndoubleArray (Integer a, Integer b, double[] worth) {

        double temp = worth[a];

        worth[a] = worth[b];

        worth[b] = temp;

        return worth;

    }

    public static ArrayList<String> Unsorting (ArrayList<String> StringArray, ArrayList<String> UnsortedArray) {

        StringArray.clear();

        StringArray.addAll(UnsortedArray);

        return StringArray;
    }

    public static Integer[] SwapInIntegerArray (Integer a, Integer b, Integer[] IntegerArray) {

        Integer temp = IntegerArray[a];

        IntegerArray[a] = IntegerArray[b];

        IntegerArray[b] = temp;

        return IntegerArray;
    }
}
