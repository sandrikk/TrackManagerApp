package lists;

public class RecList<T> {

    private T data;
    private RecList<T> rest;

    public T getData() {
        return data;
    }

    public void add(T newData){
        if(newData == null){
            throw new IllegalArgumentException("Recursive list cannot store null elements");
        } else if(isEmpty()){
            this.data = newData;
            this.rest = new RecList<>();
        } else {
            this.rest.add(newData);
        }
    }

    public boolean isEmpty() {
        assert (data == null) == (rest == null) : "Data and rest both should be filled or be empty";
        return data == null;
    }


    public int getCount() {
        if (isEmpty()) {
            return 0;

        } else {
            return 1 + rest.getCount();
        }
    }

    public boolean contains(T dataToFind) {
        if (dataToFind == null) {
            throw new IllegalArgumentException("Cannot search for null elements");
        }
        if (isEmpty()) {
            return false;
        }
        if (data.equals(dataToFind)) {
            return true;
        } else {
            return rest.contains(dataToFind);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            assert rest != null : "Rest cannot be null when data is present";
            return "{" + getData().toString() + ", " + rest.toString() + "}";
        }
    }




}