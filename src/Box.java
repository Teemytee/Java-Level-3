import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> box = new ArrayList<T>();

    public float getWeight(){
        float totalWeight = 0;
        for (int i = 0; i < box.size(); i++) {
            totalWeight += box.get(i).getWeight();
        }
        return totalWeight;
    }

    public boolean Compare(Box box){
        if(this.getWeight() == box.getWeight()){
            return true;
        }
        return false;
    }

    public void addFruit(T fruit){
        this.box.add(fruit);
    }

    @Override
    public String toString() {
        return "Box{" +
                "box=" + box +
                '}';
    }

    public void Pour(Box secondBox){
        for (int i = this.box.size()-1; i > -1; i--) {
            secondBox.box.add(this.box.get(i));
            this.box.remove(i);
        }
    }
}

