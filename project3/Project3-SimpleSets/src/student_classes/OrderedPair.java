package student_classes;

public interface OrderedPair<K, V> {
	public K getKey();
	public V getValue();
	public void setKey( K newKey );
	public void setValue( V newValue );

}
