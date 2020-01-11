import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    /**
     * 为了提高唯一性元素的查询效率，还想有序，可以使用HashSet的子类LinkedHashSet
     * LinkedHashSet保证还和原来加入顺序一样，且无重复元素，且查询效率高
     * @param args
     */
    public static void main(String[] args) {
        Set set = new LinkedHashSet();

        set.add("abcd");
        set.add("hahahahaha");
        set.add("java");
        set.add("xswl");

        for(Iterator iter = set.iterator(); iter.hasNext();){
            System.out.println(iter.next());
        }
    }
}
