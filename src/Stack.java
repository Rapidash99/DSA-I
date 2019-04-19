public class Stack {

    int[] stack;
    int size;
    int top;

    Stack(int occupancy) {
        this.size = occupancy;
        stack = new int[this.size];
        this.top = -1;
    }

    Stack(){
        this.size = 11;
        stack = new int[this.size];
        this.top = -1;
    }

    public void push(int element){
        this.top++;
        this.stack[top] = element;
    }

    public int pop() {
        int lastElement = this.stack[top];
        this.top--;
        return lastElement;
    }

    public int pick() {
        return this.stack[top];
    }

    public void clear(){
        for(int i = 0; i < this.size; i++){
            this.stack[i] = 0;
        }
        this.top = -1;
    }

    public boolean full(){
        return this.top == this.size - 1;
    }

    public boolean empty(){
        return top == -1;
    }

    /* P.S:
        It is solution of 3rd and 4th problems in one stack.
       P.P.S:
        I think all names very understandable and don't need a comments also.
     */

}
