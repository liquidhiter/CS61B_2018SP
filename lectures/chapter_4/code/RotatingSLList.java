public class RotatingSLList<T> extends SLList<T> {

	public RotatingSLList(T x) {
		super(x);
	}

	public RotatingSLList() {
		this(null);
		this.removeLast();
	}

	/**
	 * Returned list can't be muted when there is a mutation to the given list
	 * @param list
	 * @return
	 */
	public RotatingSLList<T> rotateRight(SLList<T> list) {
		if (list == null) {
			return null;
		}

		if (list.size() == 0) {
			return new RotatingSLList<>();
		}

		/* Very bad performance: O(N2)
		* the fastest way needs to access the first member variable
		* hm... can create a protected setter, but...
		* */
		int lenOfList = list.size() - 1; /* -1 as last element is moved separately */
		RotatingSLList<T> rotatedList = new RotatingSLList<>(list.get(lenOfList));
		for (int i = 0; i < lenOfList; ++i) {
			rotatedList.addLast(list.get(i));
		}
		return rotatedList;
	}

	/**
	 * @mutator
	 */
	public void rotateRight() {
		T x = removeLast();
		addFirst(x);
	}

	/**
	 *
	 * @param list
	 * @return
	 */
	public RotatingSLList<T> rotateLeft(SLList<T> list) {
		if (list == null) {
			return null;
		}

		if (list.size() == 0) {
			return new RotatingSLList<>();
		}

		int lenOfList = list.size();
		RotatingSLList<T> rotatedList = new RotatingSLList<>(list.get(1));
		for (int i = 2; i < lenOfList; ++i) {
			rotatedList.addLast(list.get(i));
		}
		rotatedList.addLast(list.get(0));
		return rotatedList;
	}

	/**
	 * @mutator
	 */
	public void rotateLeft() {
		T x = removeFirst();
		addLast(x);
	}
}
