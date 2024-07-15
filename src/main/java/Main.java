import java.util.Arrays;

public class Main {


	public static void main(String[] args) {

		CustomArrayListInterface<Integer> list = new CustomArrayListImpl<>(5);
		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 3);
		System.out.println("List after adding element at index 1: " + list);
		list.remove((Object) 22);
		System.out.println("List after removing element 22: " + list);
		list.remove((Object) 2);
		System.out.println("List after removing element 2: " + list);
		//list.clear();
		list.addAll(Arrays.asList(25, 35, 40));
		System.out.println("List after adding collection: " + list);
		System.out.println("Element at index 2: " + list.get(2));
		list.remove(2);
		System.out.println("List after removing element at index 2: " + list);
		list.clear();
		System.out.println("List after clear: " + list);
		System.out.println("Is list empty? " + list.isEmpty());
		list.addAll(Arrays.asList(5, 3, 8, 1, 2));
		System.out.println("List after adding elements: " + list);
		list.sort((o1, o2) -> o1 - o2);
		System.out.println("List after sorting: " + list);

	}
}
