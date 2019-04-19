import java.util.ArrayList;

public class Merge {
    public static ArrayList<String> MergeSorting(ArrayList<String> StringArray, Integer[] Positions) {
        //for () {
        for (int i = 0; i < StringArray.size(); i += 2) {
            if (Positions[i].equals(Positions[i + 1]) && Positions[i] == 1) {
                if (Integer.parseInt(StringArray.get(i)) > Integer.parseInt(StringArray.get(i + 1))) {
                    Tools.SwapInStringArrayList(i, i + 1, StringArray);
                }
            } else if (Positions[i].equals(Positions[i + 1]) && Positions[i] == 0) {
                if (StringArray.get(i).charAt(0) > StringArray.get(i + 1).charAt(0)) {
                    Tools.SwapInStringArrayList(i, i + 1, StringArray);
                }
            }
        }
        for (int i = 0; i < StringArray.size(); i += 4) {
            if (Positions[i].equals(Positions[i + 2]) && Positions[i] == 1) {
                if (Integer.parseInt(StringArray.get(i)) > Integer.parseInt(StringArray.get(i + 2))) {
                    Tools.SwapInStringArrayList(i, i + 2, StringArray);
                }
            }
        }
        //}

        /*if (Positions[0] == Positions[1]) {
            if (Integer.parseInt(StringArray.get(0)) > Integer.parseInt(StringArray.get(1))) {
                Tools.SwapInArrayList(0, 1, StringArray);
            }
        }
        if (Positions[2] == Positions[3]) {
            if (Integer.parseInt(StringArray.get(2)) > Integer.parseInt(StringArray.get(3))) {
                Tools.SwapInArrayList(2, 3, StringArray);
            }
        }*/
        return StringArray;
    }
}