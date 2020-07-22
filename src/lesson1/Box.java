package lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> content = new ArrayList<>(); // для хранения

    public void put(T fruit) {
        content.add(fruit);
    }

    public T get() {
        return content.remove(0);  //убираем первый
    }

    public float getWeight() { // метод для высчитывания веса коробки
        float weight = 0;
        for (T t : content) {
            weight += t.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) { // метод для сравнивания
        return (this.getWeight() == box.getWeight());
    }

    public void pourOutToBox(Box<T> box) { // метод для пересыпки
        while (!content.isEmpty()) {
            box.put(this.get());
        }
    }

    public int size() {
        return content.size();
    }

}
