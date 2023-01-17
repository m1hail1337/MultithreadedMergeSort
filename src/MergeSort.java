import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.*;

public class MergeSort {

    private static final List<Long> PERFORMANCE = new ArrayList<>();
    private static final int MAX_SIZE = 10000000;
    static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() < 2)
            return list;
        List<Integer> result = new ArrayList<>(list.size());
        int mid = list.size() / 2;
        List<Integer> left = mergeSort(list.subList(0, mid));
        List<Integer> right = mergeSort(list.subList(mid, list.size()));
        int i = 0;
        int j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) > right.get(j)) {
                result.add(right.get(j));
                j += 1;
            }
            else {
                result.add(left.get(i));
                i += 1;
            }
        }
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));
        return result;
    }

    static void merge(List<Integer> left, List<Integer> right, List<Integer> list) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()){
            if (left.get(i) < right.get(j)) {
                list.set(k, left.get(i));
                i += 1;
            }
            else {
                list.set(k, right.get(j));
                j += 1;
            }
            k += 1;
        }

        //remaining
        while (i < left.size()) {
            list.set(k, left.get(i));
            i += 1;
            k += 1;
        }
        while (j < right.size()) {
            list.set(k, right.get(j));
            j += 1;
            k += 1;
        }
    }

    static void mergeSort2T(List<Integer> list) throws InterruptedException {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(0);
        }
        int mid = list.size() / 2;

        long begin = System.currentTimeMillis();
        SortTask left = new SortTask(list.subList(0, mid));
        SortTask right = new SortTask(list.subList(mid, list.size()));
        Thread thread1 = new Thread(left);
        Thread thread2 = new Thread(right);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        merge(left.getSortedList(), right.getSortedList(), result);
        long end = System.currentTimeMillis();
        PERFORMANCE.add(end - begin);
    }

    static void mergeSort4T(List<Integer> list) throws InterruptedException {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(0);
        }

        int sep = list.size() / 4;
        List<Integer> firstHalf = result.subList(0,2*sep);
        List<Integer> secondHalf = result.subList(0,2*sep);

        long begin = System.currentTimeMillis();
        SortTask firstPart = new SortTask(list.subList(0, sep));
        SortTask secondPart = new SortTask(list.subList(sep, 2*sep));
        SortTask thirdPart = new SortTask(list.subList(2*sep, 3*sep));
        SortTask fourthPart = new SortTask(list.subList(3*sep, 4*sep));
        Thread thread1 = new Thread(firstPart);
        Thread thread2 = new Thread(secondPart);
        Thread thread3 = new Thread(thirdPart);
        Thread thread4 = new Thread(fourthPart);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        merge(firstPart.getSortedList(), secondPart.getSortedList(), firstHalf);
        merge(thirdPart.getSortedList(), fourthPart.getSortedList(), secondHalf);
        merge(firstHalf, secondHalf, result);
        long end = System.currentTimeMillis();
        PERFORMANCE.add(end - begin);
    }

    static void mergeSort8T(List<Integer> list) throws InterruptedException {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(0);
        }

        int sep = list.size() / 8;
        List<Integer> firstHalf = result.subList(0,4*sep);
        List<Integer> secondHalf = result.subList(0,4*sep);
        List<Integer> firstQuart = result.subList(0,2*sep);
        List<Integer> secondQuart = result.subList(0,2*sep);
        List<Integer> thirdQuart = result.subList(0,2*sep);
        List<Integer> fourthQuart = result.subList(0,2*sep);

        long begin = System.currentTimeMillis();
        SortTask firstPart = new SortTask(list.subList(0, sep));
        SortTask secondPart = new SortTask(list.subList(sep, 2*sep));
        SortTask thirdPart = new SortTask(list.subList(2*sep, 3*sep));
        SortTask fourthPart = new SortTask(list.subList(3*sep, 4*sep));
        SortTask fifthPart = new SortTask(list.subList(4*sep, 5*sep));
        SortTask sixthPart = new SortTask(list.subList(5*sep, 6*sep));
        SortTask seventhPart = new SortTask(list.subList(6*sep, 7*sep));
        SortTask eightPart = new SortTask(list.subList(7*sep, 8*sep));
        Thread thread1 = new Thread(firstPart);
        Thread thread2 = new Thread(secondPart);
        Thread thread3 = new Thread(thirdPart);
        Thread thread4 = new Thread(fourthPart);
        Thread thread5 = new Thread(fifthPart);
        Thread thread6 = new Thread(sixthPart);
        Thread thread7 = new Thread(seventhPart);
        Thread thread8 = new Thread(eightPart);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        merge(firstPart.getSortedList(), secondPart.getSortedList(), firstQuart);
        merge(thirdPart.getSortedList(), fourthPart.getSortedList(), secondQuart);
        merge(fifthPart.getSortedList(), sixthPart.getSortedList(), thirdQuart);
        merge(seventhPart.getSortedList(), eightPart.getSortedList(), fourthQuart);
        merge(firstQuart, secondQuart, firstHalf);
        merge(thirdQuart, fourthQuart, secondHalf);
        merge(firstHalf, secondHalf, result);
        long end = System.currentTimeMillis();
        PERFORMANCE.add(end - begin);
    }

    public static void main(String[] args) throws InterruptedException, PythonExecutionException, IOException {
        List<Integer> sizeList = new ArrayList<>();

        for (int i = MAX_SIZE / 10; i <= MAX_SIZE; i += MAX_SIZE / 10) {
            testMultithreadingSort(i);
            sizeList.add(i);
        }
        System.out.println(sizeList);
        System.out.println(PERFORMANCE);
        List<Long> summary1T = new ArrayList<>();
        List<Long> summary2T = new ArrayList<>();
        List<Long> summary4T = new ArrayList<>();
        List<Long> summary8T = new ArrayList<>();
        for (int i = 0; i < PERFORMANCE.size(); i++) {
            int index = i % 4;
            switch (index) {
                case 0 -> summary1T.add(PERFORMANCE.get(i));
                case 1 -> summary2T.add(PERFORMANCE.get(i));
                case 2 -> summary4T.add(PERFORMANCE.get(i));
                case 3 -> summary8T.add(PERFORMANCE.get(i));
            }
        }
        System.out.println(summary1T);
        Plot plt = Plot.create();
        plt.plot()
                .add(sizeList,summary1T)
                .label("1 Thread")
                .color("blue");
        plt.plot()
                .add(sizeList,summary2T)
                .label("2 Thread")
                .color("red");
        plt.plot()
                .add(sizeList,summary4T)
                .label("4 Thread")
                .color("yellow");
        plt.plot()
                .add(sizeList,summary8T)
                .label("8 Thread")
                .color("green");
        plt.xlabel("List size");
        plt.ylabel("Time(ms)");
        plt.title("Summary");
        plt.legend();
        plt.show();
    }


    static void testMultithreadingSort (int size) throws InterruptedException {
        List<Integer> testList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            testList.add(i);
        }
        Collections.shuffle(testList);
        List<Integer> shuffledList = new ArrayList<>(testList);

        // 1-Thread
        long begin = System.currentTimeMillis();
        mergeSort(shuffledList);
        long end = System.currentTimeMillis();
        PERFORMANCE.add(end - begin);
        // 2-Threads
        mergeSort2T(shuffledList);
        // 4-Threads
        mergeSort4T(shuffledList);
        // 8-Threads
        mergeSort8T(shuffledList);
    }
}


