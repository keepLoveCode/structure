package com.grubby.klc.stack;

/**
 * 基于数组实现的栈
 *
 * @author grubby
 * @date 2021/5/23 16:30
 */
public class ArrayStack {

    private int[] array;

    private int count;

    public ArrayStack(int length) {
        array = new int[length];
        count = 0;
    }

    public boolean push(int value) {
        if (isFull()) {
            return false;
        }
        array[count++] = value;
        return true;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return array[--count];
    }
    private boolean isFull() {
        return count >= array.length;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        int pop = arrayStack.pop();
        System.out.println(pop);
        arrayStack.push(1);
        int pop1 = arrayStack.pop();
        System.out.println(pop1);
        for (int i = 0; i <10 ; i++) {
            arrayStack.push(i);
        }
        boolean push = arrayStack.push(11);
        System.out.println("push : result " + push);
        int pop2 = arrayStack.pop();
        System.out.println(pop2);
    }
}
