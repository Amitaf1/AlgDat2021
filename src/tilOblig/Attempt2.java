package tilOblig;

import java.util.Arrays;

public class Attempt2 {

    public static void main(String[] args) {

        int[] a ={12, 17, 70, 15, 22, 65, 21, 90};

        System.out.println("Original Array: ");

        for (int i = 0; i < a.length; i++) {

            System.out.print(a[i] + ", ");
        }

        delsortering(a);

        System.out.println("nArray after separating even and odd numbers : ");


        for (int i = 0; i < a.length; i++) {

            System.out.print(a[i]+" ");
        }
    }


    public static void delsortering(int a[]){

        int left = 0;
        int right = a.length - 1;


        for (int i = 0; i < a.length; i++) {

            while(a[left] % 2 == 0){

                left++;
            }
            while(a[right] % 2 == 1){

                right--;

            }

            if(left < right){

                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}