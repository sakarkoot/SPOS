import java.util.ArrayList;

public class LRU_algo{

    public static void main(String[] args) {

        int max_pages = 4;
        int faults = 0;
        int cnt = 0;
        int[] page_ref_array = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};

        ArrayList<Integer> container = new ArrayList<>();

        for(Integer x:page_ref_array) {

            if(!container.contains(x)) {

                if(container.size() == max_pages) {
                    container.remove(0);
                    ++faults;
                    container.add(max_pages-1 ,x);

                }else{
                    container.add(cnt, x);
                    cnt++;
                    ++faults;
                }

            }else{
                container.remove(x);
                container.add(container.size(),x);

            }

        }
        System.out.println(faults);


    }
}
