import java.util.ArrayList;

public class Selection {
    public static Integer[] SelectionSorting(Integer[] IntegerArray) {

        for (int i = 0; i < IntegerArray.length - 1; i++) {

            int min = i;

            for (int k = i + 1; k < IntegerArray.length; k++) {

                if (IntegerArray[k] != null) {

                    if (IntegerArray[k] < IntegerArray[min]) {
                        min = k;
                    }
                }
            }

            Tools.SwapInIntegerArray(i, min, IntegerArray);

        }

        return IntegerArray;
    }
}