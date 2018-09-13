package cn.np.algorithm;

/**
 * @author np
 * @date 2018/9/13
 *
 * 冒泡排序实现
 */
public class BubbleSortDemo {

    public static void bubbleSort(int[] a){
        if(null==a || a.length<2){
            return;
        }

        for(int i=0 ; i<a.length-1 ;i++){
            for(int j=0 ;j<a.length-1-i  ;j++){
                if(a[j] > a[j+1]){
                    int tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] a={5 ,2, 8, 9 ,2, 3, 4, 9};
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        bubbleSort(a);
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
}
