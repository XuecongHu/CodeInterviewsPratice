package chapter2;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * Created by frank on 15-12-15.
 */
public class Strings {

    /**
     * 时间复杂度为O(n)，从后到前进行替换
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        if( str == null || str.length() == 0)
            return "";
        if (str.toString().equals(" "))
            return "%20";

        int blankNumbers = 0;
        for(int i=0;i<str.length();i++){
            if( str.charAt(i) == ' '){
                blankNumbers++;
            }
        }
        if(blankNumbers >0){
            int originalLength = str.length();
            int newLength = originalLength + 2 * blankNumbers;
            str.setLength(newLength);

            int p1 = originalLength - 1;
            int p2 = newLength - 1;

            while(p1>=0 && p2>p1){ //p2<=p1时，不需要复制，已经完全相同
                char cp1 = str.charAt(p1);
                if(cp1 !=' '){
                    str.setCharAt(p2, cp1);
                    p1--;
                    p2--;
                }else{
                    str.setCharAt(p2--, '0');
                    str.setCharAt(p2--, '2');
                    str.setCharAt(p2--, '%');
                    p1--;
                }
            }
        }
        return str.toString();
    }


    public int[] insertArray(int[] A1, int len, int[] A2){
        if(A1==null&&A2==null)
            return new int[0];
        if((A1==null||A1.length==0)&&A2.length>0)
            return A2;
        if((A2==null||A2.length==0)&&A1.length>0)
            return A1;
        int a1 = len - 1;
        int a2 = A2.length - 1;
        int index = len + A2.length - 1;

        while(a1>=0&&a2>=0){
            if(A1[a1]>=A2[a2]){
                A1[index] = A1[a1];
                a1--;
            }else{
                A1[index] = A2[a2];
                a2--;
            }
            index--;
        }

        if(a2>=0){
            for(int i=a2;i>=0;i--)
                A1[index--] = A2[i];
        }

        return A1;
    }

    public static void testReplaceSpace(){
        Strings s = new Strings();
        System.out.println(s.replaceSpace(
                new StringBuffer("     ")));
        System.out.println(s.replaceSpace(
                new StringBuffer("We are happy.")));
        System.out.println(s.replaceSpace(
                new StringBuffer("Wearehappy.")));
        System.out.println(s.replaceSpace(null));
        System.out.println(s.replaceSpace(
                new StringBuffer("")));
        System.out.println(s.replaceSpace(
                new StringBuffer(" ")));
    }

    static void printArrays(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println("");
    }
    static void testInsertArray(){
        Strings s = new Strings();
        int[] A1 = {4,5,6,0,0,0,0,0,0,0,0,0};
        int[] A2 = {1,2,3};
        printArrays(s.insertArray(A1, 3, A2));
    }

    public static void main(String[] args) {
        testReplaceSpace();
        testInsertArray();
    }
}
