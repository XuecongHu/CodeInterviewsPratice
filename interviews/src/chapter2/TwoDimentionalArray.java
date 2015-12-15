package chapter2;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * Created by frank on 15-12-15.
 */

public class TwoDimentionalArray {

    /**
     * 对于一个在矩阵中的任意元素，比该元素要大或要小的区域都很大，而且存在多个可能存在的区域，无法缩小范围。
     * 关键在于限制方向，所以从右上角或者左下角开始，能够限制缩小的范围。
     * @param array 二维数组
     * @param target 要判断含有的整数
     * @return 二维数组是否含有该整数
     */
    public boolean Find(int [][] array, int target){
        if(array==null || array.length==0)  //边界条件
            return false;

        boolean result = false;
        int columns = array.length; //二维数组列长度
        int rows = array[0].length; //二维数组行长度

        //从二维数组的右上角开始寻找
        int r = 0;
        int c = columns - 1;
        while( r<rows && c>=0 ){
            if(array[r][c]==target) { //相等立即跳出循环
                result = true;
                break;
            }
            if( array[r][c]>target ) //右上角大于目标则剔除所在的列，往左移
                c--;
            else if ( array[r][c]<target )//右上角小于目标则剔除所在的行，往下移
                r++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}}; //二维数组初始化
        TwoDimentionalArray ta = new TwoDimentionalArray();
        System.out.println(ta.Find(null, 20));
        System.out.println(ta.Find(array, 7));
        System.out.println(ta.Find(array, 20));
    }
}

