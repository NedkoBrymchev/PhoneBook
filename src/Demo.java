import java.io.File;

public class Demo {
	

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		File file = new File("C:\\Users\\nedko.bramchev\\Desktop\\test.txt");
		PhoneBook phoneBook = new PhoneBook(file);
		
		phoneBook.removeContact("Ivan Ivanov");
		phoneBook.removeContact("Iva Ivanov");
		phoneBook.removeContact("Georgi Ivanov");
		phoneBook.removeContact("Dragan Ivanov");
		phoneBook.printContactsSorted();
		System.out.println("The number of Nedko Brymchev is " + phoneBook.getNumberOfTheContact("Nedko Brymchev"));
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/10000);
		phoneBook.makeCall(new Contact("Nedko Brymchev","0883415111"));
		phoneBook.makeCall(new Contact("Nedko Brymchev","0883415111"));
		phoneBook.makeCall(new Contact("Nedko Brymchev","0883415111"));
		phoneBook.makeCall(new Contact("Nedko Brymcev","0883415111"));
		phoneBook.mostCalledContacts();
		
	}
		
}

