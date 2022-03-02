package com.company;

public class MaxHeap implements Heap{
    public static void heapify(int array[]){
        for (int i = array.length/2; i > 0 ; i--) {
            percolateUp(array,i);
        }
    }

    public static int removeMax(int[]array){
        int max = array[0];

        int newArray[] = new int[array.length-1];
        for (int i:
             newArray) {
            newArray[i] = array[i+1];
        }
        array = newArray;
        heapify(array);

        return max;
    }

    public static void percolateUp(int[]array, int i){
        while(i > 0){
            if(array[i/2] < array[i]){
                swap(array,i,i/2);
                percolateDown(array,i);
                i/=2;
            }else
                break;
        }
    }

    public static void percolateDown(int []array, int i){
        int maxChildIndex;
        while(2*i < array.length || 2*i + 1 < array.length){
            if(2*i + 1 < array.length){
                if(array[2*i] > array[2*i+1])
                    maxChildIndex = 2*i;
                else
                    maxChildIndex = 2*i + 1;
            }else
                maxChildIndex = 2*i;

            if(array[i] < array[maxChildIndex]){
                swap(array,i,maxChildIndex);
                percolateUp(array,i);
                i*=2;
            }else
                break;

        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void heapify(int array[], int rightBound){
        for (int i = rightBound/2; i > 0 ; i--) {
            percolateUp(array,i,rightBound);
        }
    }

    public static void percolateUp(int[]array, int i, int rightBound){
        while(i > 0){
            if(array[i/2] < array[i]){
                swap(array,i,i/2);
                percolateDown(array,i,rightBound);
                i/=2;
            }else
                break;
        }
    }

    public static void percolateDown(int []array, int i, int rightBound){
        int maxChildIndex;
        while(2*i < rightBound || 2*i + 1 < rightBound){
            if(2*i + 1 < rightBound){
                if(array[2*i] > array[2*i+1])
                    maxChildIndex = 2*i;
                else
                    maxChildIndex = 2*i + 1;
            }else
                maxChildIndex = 2*i;

            if(array[i] < array[maxChildIndex]){
                swap(array,i,maxChildIndex);
                percolateUp(array,i);
                i*=2;
            }else
                break;

        }
    }
}
