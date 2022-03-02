package com.company;

import java.util.Arrays;

public class SortingAlgorithms {

    public static void mainSortingAlgorithms() {
        int [] array = {45,12,85,32,89,39,69,44,42,1,6,8};
        int [] array1 = {45,12,85,32,89,39,69,44,42,1,6,8};
        int [] array2 = {45,12,85,32,89,39,69,44,42,1,6,8};
        int [] array3 = {45,12,85,32,89,39,69,44,42,1,6,8};
        int [] array4 = {45,12,85,32,89,39,69,44,42,1,6,8};
        int [] array5 = {45,12,85,32,89,39,69,44,42,1,6,8};
        int [] array6 = {45,12,85,32,89,39,69,44,42,1,6,8};
        Arrays.sort(array);
        printArray(array);
        SortingAlgorithms.bubbleSort(array1);
        printArray(array1);
        SortingAlgorithms.selectionSort(array2);
        System.out.println("      Selection Sort");
        printArray(array2);
        System.out.println();

        SortingAlgorithms.insertionSort(array3);
        System.out.println("      Insertion Sort");
        printArray(array3);
        System.out.println();

        SortingAlgorithms.mergeSort(array4);
        System.out.println("      Merge Sort");
        printArray(SortingAlgorithms.mergeSort(array4));
        System.out.println();

        quickSort(array5);
        System.out.println("      Quick Sort");
        printArray(array5);
        System.out.println();

        heapSort(array6);
        System.out.println("      Heap Sort");
        printArray(array6);
        System.out.println();
    }

    public static void findDepth_Of_Each_Node(int []array, int[] ans, int left, int right, int rootDepth){
        if(left <= right){
            int max = array[left], maxIndex = left;
            for (int i = left+1; i <= right; i++) {
                if(max < array[i]){
                    max = array[i];
                    maxIndex = i;
                }
            }
            ans[maxIndex] = ++rootDepth;
            findDepth_Of_Each_Node(array,ans,left,maxIndex-1,rootDepth);
            findDepth_Of_Each_Node(array,ans,maxIndex+1,right, rootDepth);
        }
    }

    public static int maxSets(int []array){
        array = radixSort(array);
        int maxSets = 0, currentSetLength = 0;

        for (int i = 0; i < array.length;) {
            currentSetLength = array[i];
            for (int j = i; j < currentSetLength + i && j < array.length; j++) {
                if(currentSetLength < array[j])
                    currentSetLength = array[j];
            }
            i += currentSetLength;
            if(i < array.length)
                maxSets++;
        }

        return maxSets;
    }

    public static int[] radixSort(int[] array){
        int mod = 10;
        int maxDigit = 0;

        for (int i = 0; i < array.length; i++) {
            if(maxDigit < String.valueOf(array[i]).length())
                maxDigit = String.valueOf(array[i]).length();
        }//get the length of largest integer which is the time that while loop will run
        while(maxDigit > 0){
            array = countingSort_For_Radix(array,mod);
            mod *= 10;
            maxDigit--;
        }// each time this runs, it sort based on the digit's place

        return array;
    }

    private static int[] countingSort_For_Radix(int []array, int mod){
        int accumulator = 0; //count the number of integers smaller than current value
        int []ans = new int[array.length];
        int []k = new int [10]; // store the number of times that digit "i" appears
        int []currentDigit = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            currentDigit[i] = array[i];
        }

        while(mod != 10){
            for (int i = 0; i < array.length; i++) {
                if(array[i] > 9)
                    currentDigit[i] = currentDigit[i] / 10;
                else
                    currentDigit[i] = 0;
            }
            mod /= 10;
        }
        for (int i = 0; i < array.length; i++) {
            k[currentDigit[i] % mod]++;
        }


        for (int i = 0; i < k.length; i++) {
            if(k[i] != 0){
                k[i] += accumulator;
                accumulator = k[i];
            }
        }

        for (int i = array.length - 1; i >= 0; i--) {
            ans[--k[currentDigit[i] % mod]] = array[i];
        }

        return ans;
    }

    public static int[] countingSort(int []array){
        int max = 0, accumulator = 0;
        int[] ans = new int[array.length];
        int []k;

        for (int i = 0; i < array.length; i++) {
            if(max < array[i])
                max = array[i];
        }
        k = new int[max+1];

        for (int i = 0; i < array.length; i++) {
            k[array[i]]++;
        }


        for (int i = 0; i < k.length; i++) {
            if(k[i] != 0){
                k[i] += accumulator;
                accumulator = k[i];
            }
        }

        for (int i = 0; i < array.length; i++) {
            ans[--k[array[i]]] = array[i];
        }

        return ans;
    }


    public static void heapSort(int [] array){
        MaxHeap.heapify(array);
        for (int i = array.length - 1; i > 0 ; i--) {
            swap(array,0,i);
            MaxHeap.heapify(array,i);
        }
    }

    public static void bubbleSort(int []array){
        boolean unsorted = true;
        while(unsorted){
            unsorted = false;
            for (int i = 0; i < array.length - 1; i++) {
                if(array[i] > array[i+1]){
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    unsorted = true;
                }
            }
        }
    }

    public static void selectionSort(int []array){
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if(min > array[j]){
                    min = array[j];
                    minIndex = j;
                }// find min each iteration and swap to its place
            }
            if(minIndex == i) continue;
            array[minIndex] = array[i];
            array[i] = min;
        }
    }

    public static void insertionSort(int [] array){
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i] > array[i+1]){
                current = array[i+1];
                for (int j = i+1; j >= 0; j--) {
                    if(j == 0) {
                        array[j] = current;
                        break;
                    }
                    array[j] = array[j-1];
                    if(array[j-1] < current){
                        array[j] = current;
                        break;
                    }

                }
            }
        }
    }

    public static int[] mergeSort(int []array){
        if(array.length == 1){
            return array;
        }
        int midpoint = array.length/2;
        int left[] = new int[midpoint];
        int right[];
        int res[];

        if(array.length % 2 == 0)
            right = new int[midpoint];
        else
            right = new int[midpoint + 1];

        for (int i = 0; i < midpoint; i++) {
            left[i] = array[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = array[midpoint + i];
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return mergeArray(left,right);
    }

    public static int[] mergeArray(int a[], int b[]){
        int aSize = a.length, bSize = b.length;
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while(i < aSize && j < bSize){
            if(a[i] < b[j])
                c[k++] = a[i++];
            else
                c[k++] = b[j++];
        }
        for (; i < aSize; i++)
            c[k++] = a[i];
        for (; j < bSize; j++)
            c[k++] = b[j];
        return c;
    }

    public static  void quickSort(int array[]){
        quickSort(array,0,array.length);
    }

    public static void quickSort(int []array,int left, int right){
        if(left < right){
            int j = partition(array,left,right);// find the index of pivot while the pivot is sorted in partition method
            quickSort(array,left,j);
            quickSort(array,j+1,right);
        }
    }

    public static int partition(int[] array, int left, int right) {
        int i = left, j = right, pivot = array[left];
        while(i < j){
            do{
                i++;
            }while(array[i] < pivot && i+1 < right);// find the number on the left side that is larger than pivot

            do{
                j--;
            }while(array[j] > pivot);// find the number on the right side that is smaller than pivot

            if(i < j){
                swap(array,i,j);// swap them, there is a if statement because j will less than i in last iteration
            }
        }
        swap(array,left,j);// j is the index of pivot because all the nums larger than pivot is on the right side of j
        return j;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printArray(int [] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
