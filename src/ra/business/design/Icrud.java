package ra.business.design;

import java.util.List;
import java.util.Scanner;

public interface Icrud<T,E> {
    boolean create(T t);
    boolean update(T t);
    boolean delete(E e);
    List<T> readFromfile();
    boolean writeToFile(List<T> list);
    T inputData(Scanner sc);
    void displayData();
}
