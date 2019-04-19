import java.util.ArrayList;

public class Bubble {
    public static String BubbleSorting (ArrayList<String> StringArray, Integer[] Positions) {
        for (int i = 0; i < StringArray.size() - 1; i ++) {
            for (int k = 0; k < StringArray.size() - i - 1; k ++) {
                if (Positions[k] == 1) {
                    for (int l = k + 1; l < Positions.length; l ++) {
                        if (Positions[l] == Positions[k]) {
                            if (Integer.parseInt(StringArray.get(k)) > Integer.parseInt(StringArray.get(l))) {
                                Tools.SwapInStringArrayList(k, l, StringArray);
                            }

                            break;
                        }
                    }
                }
                else if (Positions[k] == 0) {
                    for (int l = k + 1; l < Positions.length; l ++) {
                        if (Positions[l] == Positions[k]) {
                            if (StringArray.get(k).charAt(0) > StringArray.get(l).charAt(0)) {
                                Tools.SwapInStringArrayList(k, l, StringArray);
                            }
                        }
                    }
                }
            }
        }

        String BubbleSortedString = "";

        for (int i = 0; i < StringArray.size(); i ++) {
            BubbleSortedString = BubbleSortedString + StringArray.get(i) + " ";
        }

        return BubbleSortedString;
    }
}
