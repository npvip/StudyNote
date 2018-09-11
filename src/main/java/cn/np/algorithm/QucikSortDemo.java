package cn.np.algorithm;

/**
 * @author np
 * @date 2018/9/11
 *
 * 快速排序
 */
public class QucikSortDemo {


    public static void qucikSort(int[] a,int low,int high){

        if(null==a || a.length<2){
            return;
        }
        //递归出口
        if(low>=high){
            return;
        }

        int i=low;
        int j=high;
        int key=a[i];

        while (i<j){

            while (i<j && a[j]>key){
                j--;
            }

            while (i<j && a[i]<=key){
                i++;
            }

            if(i<j){
                int tmp=a[i];
                a[i]=a[j];
                a[j]=tmp;
            }
        }

        int p=a[i];
        a[i]=a[low];
        a[low]=p;

        qucikSort(a,low,i-1);
        qucikSort(a,i+1,high);

    }

    public static void main(String[] args) {
        int[] a={5 ,2, 8, 9 ,2, 3, 4, 9};
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        qucikSort(a,0,a.length-1);
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
}
