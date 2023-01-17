import java.util.List;

public class SortTask implements Runnable {
    private List<Integer> list;

    public SortTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        this.list = MergeSort.mergeSort(this.list);
    }

    public List<Integer> getSortedList() { return list; }

}
