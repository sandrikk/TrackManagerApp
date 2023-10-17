package maps;

import lists.DoublyLinkedList;
import lists.Node;

public class HashMap<K, V> {
    private final DoublyLinkedList<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        buckets = new DoublyLinkedList[10];
        size = 0;
    }

    public void put(K key, V value) {
        if (value == null)
            throw new NullPointerException();
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new DoublyLinkedList<>();
        }

        // Check if the key already exists in the bucket
        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        Node<Entry<K, V>> node = bucket.getHead();
        while (node != null) {
            if (node.getData().getKey().equals(key)) {
                node.getData().setValue(value);
                return;
            }
            node = node.getNext();
        }

        // Key not found, add a new entry to the bucket
        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            throw new NullPointerException();
        }

        // Search for the key in the bucket
        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        Node<Entry<K, V>> entry = bucket.getHead();
        while (entry != null) {
            if (entry.getData().getKey().equals(key)) {
                return entry.getData().getValue();
            }
            entry = entry.getNext();
        }

        // Key not found in the bucket
        throw new NullPointerException();
    }

    private int hash(K key) {
        // Implement your hash function here
        // You can use the key's hash code or create a custom hash function
        return Math.abs(key.hashCode() % buckets.length);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}