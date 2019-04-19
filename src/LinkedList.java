import java.io.File;
        import java.util.ArrayList;
        import java.util.Scanner;

public class LinkedList {
    class Cell
    {
        Integer variable;
        Cell next;
        Cell previous;
    }

    Cell root = new Cell();

    public Cell findLast(Cell current) {
        if (current.next == null)
        {
            return current;
        }
        else
        {
            return findLast(current.next);
        }
    }

    public void addCell(Integer value)
    {
        Cell newNode = new Cell();
        Cell lastCell = findLast(root);

        newNode.previous = lastCell;
        lastCell.next = newNode;

        newNode.variable = value;
    }

    public void inputerator(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            addCell(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        LinkedList list = new LinkedList();
        list.addCell(1);


        ArrayList<Integer> array = new ArrayList<>();
        Scanner sc = new Scanner(new File("input.txt"));

        int i = 0;
        while (sc.hasNextInt()) {
            array.add(sc.nextInt());
            i++;
        }

    }
}