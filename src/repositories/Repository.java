package repositories;


import entities.Student;

import java.util.List;

public interface Repository<T> {
    public void addElement(T obj);

    public void addElements(List<T> objs);

    public void removeElement(long id);

    public void displayElements();

}
