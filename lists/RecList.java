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
        }
        else if(isEmpty()){
            this.data = newData;
            this.rest = new RecList<>();
        }else{
            // this about this...
            assert false : "this part hasn't been implemented";

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

    public boolean contains(T data) {
        if (data == null) {
            // this.data.equals(data);
            // Homework : (
            return false;
        }
        return false;
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