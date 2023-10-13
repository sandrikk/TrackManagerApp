/*
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

    private static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new DoublyLinkedList<>();
        }

        // Check if the key already exists in the bucket
        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        Node<Entry<K, V>> node = bucket.getHead();
        while (node != null) {
            if (node.data.getKey().equals(key)) {
                node.data.setValue(value);
                return;
            }
            node = node.next;
        }

        // Key not found, add a new entry to the bucket
        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }

        // Search for the key in the bucket
        DoublyLinkedList<Entry<K, V>> bucket = buckets[index];
        Node<Entry<K, V>> entry = bucket.getHead();
        while (entry != null) {
            if (entry.data.getKey().equals(key)) {
                return entry.data.getValue();
            }
            entry = entry.next;
        }

        // Key not found in the bucket
        return null;
    }


    private int hash(K key) {
        // Implement your hash function here
        // You can use the key's hash code or create a custom hash function
        return Math.abs(key.hashCode() % buckets.length);
    }

}

 */

