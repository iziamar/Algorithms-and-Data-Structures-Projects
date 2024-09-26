public class SparseVector {
	private Node head;
	private int length;
	public SparseVector(int len){
		head = null;
		length = len;
	}

	// Prints out a sparse vector (including zeros)
	public String toString(){

		String result = "";
		Node currNode = head;
		int currIndex = 0;
		while( currNode != null ){
			int idx = currNode.getIndex();

			// Pad the space between nodes with zero
			while( currIndex < idx ){
				result += "0, ";
				currIndex++;
			}
			result += currNode;
			currNode = currNode.getNext();
			currIndex++;

			// Only add a comma if this isn't the last element
			if( currNode != null ){ result += ", "; }
		}
		return result;
	}

	// TODO: Implement me for milestone 2
	public void addElement(int index, double value){
		Node newest = new Node(index, value);
		if (head == null) {
			head = newest;
			head.setNext(null);
		} else {
			if (index >= length) {
				System.out.println("invalid index value");
				return;
			} else {
				if (head.getNext() == null) {
					head.setNext(newest);
				} else {
					Node last = head.getNext();
					last.setNext(newest);
				}
			}
		}
	}

	// TODO: Implement me for milestone 4
	public static double dot( SparseVector x, SparseVector y ){
		Node xCurrNode = x.head;
		Node yCurrNode = y.head;
		double result = 0.0;
		if (x.length == y.length) {
			while (xCurrNode != null && yCurrNode != null){
				int idxx = xCurrNode.getIndex();
				int idxy = yCurrNode.getIndex();
				if (idxy == idxx){
					result += xCurrNode.getValue() * yCurrNode.getValue();
					xCurrNode = xCurrNode.getNext();
					yCurrNode = yCurrNode.getNext();
				}
				else if (idxy < idxx) {
					yCurrNode = yCurrNode.getNext();
					idxy = yCurrNode.getIndex();
				}
				else {
					xCurrNode = xCurrNode.getNext();
					idxx = xCurrNode.getIndex();
				}
			}
			return result;
		}
		else {
				System.out.println("Invalid input, try again.");
				return 0.0;
		}
	}


	// TODO: Test out your code here!
	public static void main(String[] args) {

		SparseVector x = new SparseVector(100000000);
		x.addElement(0, 1.0);
		x.addElement(10000000, 3.0);
		x.addElement(10000001, -2.0);
		SparseVector y = new SparseVector(100000000);
		y.addElement(0, 2.0);
		y.addElement(10000001, -4.0);
		double result = dot(x, y);
		System.out.println(result);
	}
}


