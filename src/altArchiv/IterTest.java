package altArchiv;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
 
public class IterTest {
 
  public static void main(String[] args) {
   
    //create HashMap object
    HashMap hMap = new HashMap();
   
    //add key value pairs to HashMap
    hMap.put("1","One");
    hMap.put("2","Two");
    hMap.put("3","Three");
   
    /*
      get Collection of values contained in HashMap using
      Collection values() method of HashMap class
    */
    Collection c = hMap.values();
   
    //obtain an Iterator for Collection
    Iterator itr = c.iterator();
   
    //iterate through HashMap values iterator
    while(itr.hasNext())
      System.out.println(itr.next());
  }
}