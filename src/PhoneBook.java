import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PhoneBook {
	private ArrayList<Contact> contacts = new ArrayList<>();
	private TreeMap<String, String> contactsInMap = new TreeMap<>();
	private File file;

	public PhoneBook(File file) {

		this.file = file;
		ArrayList<String> pairs = new ArrayList<>();
		String[] pairsSplitted = new String[2];
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> phones = new ArrayList<>();

		/*
		 * Reading from file and add the names and phones in arrays
		 */

		try {
			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				pairs.add(line);

			}
			fileReader.close();

			for (int i = 0; i < pairs.size(); i++) {
				pairsSplitted = pairs.get(i).split(",");
				phones.add(pairsSplitted[1]);
				names.add(pairsSplitted[0]);
				contactsInMap.put(pairsSplitted[0], pairsSplitted[1]);

			}
			/*
			 * iterate over the phone`s array and add contacts if their number
			 * is valid
			 */
			for (int i = 0; i < phones.size(); i++) {
				if (isNumberValid(phones.get(i))) {
					this.contacts.add(new Contact(names.get(i), phones.get(i)));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * add contact by given name and number
	 */
	public void addContact(String name, String number) {
		// add the contact in the array
		this.contacts.add(new Contact(name, number));

		// add the contact in the treeMap
		// this.contactsInMap.put(name, number);

		// add the contact in the file
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(file, true));
			writer.append(name + "," + number + System.lineSeparator());
			writer.close();
		} catch (IOException e) {

		}
	}

	/*
	 * add contact by given contact
	 */
	public void addContact(Contact contact) {
		this.contacts.add(contact);

		try {
			PrintWriter writer = new PrintWriter(new FileWriter(this.file, true));
			writer.println();
			writer.append(contact.getName() + "," + contact.getNumber());
			writer.close();
		} catch (IOException e) {

		}
	}

	/*
	 * remove contact by given name
	 */
	public void removeContact(String name) {
		// remove from the array
		for (int i = 0; i < this.contacts.size(); i++) {
			if (this.contacts.get(i).getName().equals(name)) {
				this.contacts.remove(i);

			}
		}
		// removing from file
		File tempFile = new File("C:\\Users\\nedko.bramchev\\Desktop\\test1.txt");
		try {
			if (tempFile.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}
			BufferedReader reader;
			BufferedWriter writer;

			reader = new BufferedReader(new FileReader(this.file));
			writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.split(",")[0].equals(name)) {
					continue;
				}
				writer.write(currentLine + System.lineSeparator());
			}
			writer.close();
			reader.close();
			this.file.delete();
			tempFile.renameTo(this.file);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * getting the number of contact by given name
	 */
	public String getNumberOfTheContact(String name) {
		for (int i = 0; i < this.contacts.size(); i++) {
			if (this.contacts.get(i).getName().equals(name)) {
				return this.contacts.get(i).getNumber();
			}
		}
		return "There is no contact with this name in the phonebook";
	}

	public void printContactsSorted() {
		/*
		 * Bubble sorting the contacts
		 */

		// Contact tempContact = new Contact("", "");
		// for (int k = 0; k <this.contacts.size()-1; k++) {
		// for (int i = 0; i < this.contacts.size()-1; i++) {
		// if(contacts.get(i).getName().compareTo(contacts.get(i+1).getName()) >
		// 0){
		// tempContact.setName(contacts.get(i).getName());
		// tempContact.setNumber(contacts.get(i).getNumber());
		// this.contacts.remove(i);
		// this.contacts.add(i+1, tempContact);
		// }
		// }
		// }

		/*
		 * use method sort from Collections(merge or quick sort)
		 */

		Collections.sort(contacts);

		/*
		 * If we want to sort contacts in TreeMap use this
		 */
		// for(Entry<String, String> entry : contactsInMap.entrySet()) {
		// String key = entry.getKey();
		// String value = entry.getValue();
		//
		// System.out.println(key + " => " + value);
		// }

		/*
		 * print the contacts
		 */
		System.out.println("Your phonebook looks like:");
		for (int i = 0; i < this.contacts.size(); i++) {
			System.out.println(contacts.get(i).getName() + " => " + contacts.get(i).getNumber());
		}

	}

	/*
	 * making call to someone by given contact
	 */
	public void makeCall(Contact contact) {
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getName().equals(contact.getName())
					&& contacts.get(i).getNumber().equals(contact.getNumber()))
				contacts.get(i).setNumberOfOutgoingCalls(contacts.get(i).getNumberOfOutgoingCalls() + 1);
		}
	}

	/*
	 * print the most searched contacts
	 */
	public void mostCalledContacts() {
		Collections.sort(contacts, new Comparator<Contact>() {

			@Override
			public int compare(Contact o1, Contact o2) {
				return o2.getNumberOfOutgoingCalls() - o1.getNumberOfOutgoingCalls();
			}
		});
		if (contacts.size() >= 5) {
			for (int i = 0; i < 5; i++) {
				System.out.println(contacts.get(i).getName() + "," + contacts.get(i).getNumber() + " => "
						+ contacts.get(i).getNumberOfOutgoingCalls());
			}
		} else {
			for (int i = 0; i < contacts.size(); i++) {
				System.out.println(contacts.get(i).getName() + "," + contacts.get(i).getNumber() + " => "
						+ contacts.get(i).getNumberOfOutgoingCalls());
			}
		}
	}

	/*
	 * check if the given number is valid
	 */
	public boolean isNumberValid(String number) {
		if (number.charAt(0) == '+' && number.length() == 13) {
			return number.substring(1).matches("3598[7-9][2-9]\\d+");
		} else if (number.charAt(0) == '0' && number.length() == 14) {
			return number.substring(1).matches("03598[7-9][2-9]\\d+");
		} else {
			return (number.matches("08[7-9][2-9]\\d+") && number.length() == 10);
		}
	}

}
