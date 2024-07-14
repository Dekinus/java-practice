import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> implements CustomArrayListInterface<E> {

	private final static int DEFAULT_SIZE = 10;
	private final static Object[] DEFAULT_VALUES = {};
	private int size;
	private Object[] values;

	public CustomArrayList() {
		this.size = DEFAULT_SIZE;
		this.values = DEFAULT_VALUES;
	}

	public CustomArrayList(int size, E[] values) {
		this.size = size;
		this.values = values;
	}

	public CustomArrayList(int customSize) {
		if (customSize > 0) {
			this.values = new Object[customSize];
		} else if (customSize == 0) {
			this.values = DEFAULT_VALUES;
		} else {
			throw new IllegalArgumentException("Illegal size");
		}
	}

	private void newSize() {
		Object[] newValues = new Object[size * 3 / 2 + 1];
		System.arraycopy(values, 0, newValues, 0, newValues.length);
		//values = newValues;
		size = newValues.length;
	}

	private void ensureSize(int tempSize) {
		if (tempSize > values.length) {
			newSize();
			size = values.length;
		}
	}

	@Override
	public void add(int index, E element) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Illegal index");
		}
		if (index > values.length) {
			newSize();
		}
		values[index] = element;
		size++;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void addAll(Collection<? extends E> c) {
		ensureSize(size + c.size());
		for (E e : c) {
			values[size++] = e;
		}

	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			values[i] = null;
		}
		size = 0;
	}

	@Override
	public E get(int index) {
		return (E) values[index];
	}


	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void remove(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		System.arraycopy(values, index + 1, values, index, size - index - 1);
		values[--size] = null;

	}

	@Override
	public void remove(Object o) {
		for (int i = 0; i < size; i++) {
			if (values[i].equals(o)) {
				remove(i);
				return;
			}
		}
	}

	@Override
	public void sort(Comparator<? super E> c) {
		quickSort((E[]) values, 0, values.length - 1, c);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(values, size);
	}

	public void quickSort(E arr[], int begin, int end, Comparator<? super E> c) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end, c);
			quickSort(arr, begin, partitionIndex - 1, c);
			quickSort(arr, partitionIndex + 1, end, c);
		}
	}

	private int partition(E arr[], int begin, int end, Comparator<? super E> c) {
		E pivot = arr[end];
		int i = (begin - 1);
		for (int j = begin; j < end; j++) {
			if (c.compare(arr[j], pivot) < 0) {
				i++;
				E swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}
		E swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;
		return i + 1;
	}

}