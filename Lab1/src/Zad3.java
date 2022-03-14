public class Zad3 {
    public static void main(String[] args)
    {
        int[] array={ 4, 5, 7, 11, 12, 15, 15, 21, 40, 45};
        int resault=searchIndex(array,11);
        System.out.println(resault);
    }
    private static int searchIndex(int[] array,int value){
        int index=0;
        int limit= array.length-1;
        while (index<=limit){
            int point= (int)Math.ceil((index+limit)/2);
            int entry=array[point];
            if(value >entry){
                index=point+1;
                continue;
            }
            if (value<entry){
                limit=point-1;
                continue;
            }
            return point;
        }
        return -1;
    }
}

//// Usage example:
//
//    // for this implementation array must be sorted from smallest to biggest!
//    var array = [ 4, 5, 7, 11, 12, 15, 15, 21, 40, 45 ];
//        var index = searchIndex(array, 11); // we want to find index for 11
//
//        console.log(index);  // 3