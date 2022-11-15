package rc.sudokugenius.collections;

import java.util.Map.Entry;
import rc.collections.ArrayList;

public class HashMap<K, V extends ArrayList> extends java.util.HashMap<K, V> {

    @Override
    public HashMap<K, V> clone() {
        HashMap<K, V> dest = new HashMap<K, V>();

        for (Entry<K, V> entry : entrySet()) {
            dest.put(entry.getKey(), (V) entry.getValue().clone());
        }

        return dest;
    }
}