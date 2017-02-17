
public class Contact implements Comparable {
	private String name;
	private String number;
	private int numberOfOutgoingCalls;

	public Contact(String name, String number) {
		super();
		this.setName(name);
		this.setNumber(number);
		this.numberOfOutgoingCalls = 0;
	}

	public int getNumberOfOutgoingCalls() {
		return numberOfOutgoingCalls;
	}

	public void setNumberOfOutgoingCalls(int numberOfOutgoingCalls) {
		if (numberOfOutgoingCalls >= 0)
			this.numberOfOutgoingCalls = numberOfOutgoingCalls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int compareTo(Object arg0) {

		return this.name.compareTo(((Contact) arg0).getName());
	}

}
