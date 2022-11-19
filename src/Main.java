import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Node<String> cpf = null;
		Node<String> rg = null;
		Node<String> name = null;
		Node<Date> date = null;
		Node<String> city = null;

		int index = 0;

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		List<String> list = new ArrayList<String>();

		try {
			File myFile = new File("test.txt");

			Scanner fileReader = new Scanner(myFile);

			String line = fileReader.nextLine();
			String [] lineSubStrings = line.split(";");

			list.add(line);

			cpf = new Node<String>(lineSubStrings[0], index);
			rg = new Node<>(lineSubStrings[1], index);
			name = new Node<>(lineSubStrings[2], index);
			date = new Node<>(format.parse(lineSubStrings[3]), index);
			city = new Node<>(lineSubStrings[4], index);
			index++;

			while (fileReader.hasNextLine()) {
				line = fileReader.nextLine();

				list.add(line);

				lineSubStrings = line.split(";");
				cpf.insert(lineSubStrings[0], index);
				rg.insert(lineSubStrings[1], index);
				name.insert(lineSubStrings[2], index);
				date.insert(format.parse(lineSubStrings[3]), index);
				city.insert(lineSubStrings[4], index);
				index++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String[] arr = new String[list.size()];
		list.toArray(arr);

		int i = cpf.search("02102635040");

		if (i >= 0) {
			System.out.println(list.get(i));
		}

		List<Integer> list1 = name.searchName("F");

		for (Integer i1 : list1) {
			System.out.println(arr[i1]);
		}
	}
}