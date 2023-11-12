package maps;

import lists.DoublyLinkedList;
import lists.DoublyNode;

public class MyHashMap<K, V> {
    private final DoublyLinkedList<Entry<K, V>>[] buckets;
    private int size;

    public MyHashMap() {
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
        DoublyNode<Entry<K, V>> node = bucket.getHead();
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

    public boolean contains(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return false;
        }

        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        DoublyNode<Entry<K, V>> node = bucket.getHead();
        while (node != null) {
            if (node.getData().getKey().equals(key)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void remove(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return;
        }

        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        DoublyNode<Entry<K, V>> previous = null;
        DoublyNode<Entry<K, V>> current = bucket.getHead();

        while (current != null) {
            if (current.getData().getKey().equals(key)) {
                if (previous == null) {
                    // Removing the head node
                    bucket.setHead(current.getNext());
                } else {
                    // Removing a middle or last node
                    previous.setNext(current.getNext());
                }
                if (current.getNext() != null) {
                    current.getNext().setPrev(previous);
                }
                size--;
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }

    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            throw new NullPointerException("Bucket is null, key does not exist.");
        }

        // Search for the key in the bucket
        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        DoublyNode<Entry<K, V>> entry = bucket.getHead();
        while (entry != null) {
            if (entry.getData().getKey().equals(key)) {
                return entry.getData().getValue();
            }
            entry = entry.getNext();
        }

        throw new NullPointerException("Key not found in the bucket.");
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}