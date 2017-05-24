import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

interface Predicate<T> {
	boolean test(T t);
}

interface CheckPerson {
	boolean test(Person p);
}

public class Person {

	static List<Person> persons = new ArrayList<>();

	static {
		persons.add(new Person("Sachin", 40, Sex.MALE, "Sachin@xyz.com"));
		persons.add(new Person("Joan", 30, Sex.FEMALE, "Joan@xyz.com"));
		persons.add(new Person("Saurabh", 35, Sex.MALE, "Saurabh@xyz.com"));
		persons.add(new Person("Kate", 20, Sex.FEMALE, "Kate@xyz.com"));
		persons.add(new Person("Shashank", 15, Sex.MALE, "Shashank@xyz.com"));
		persons.add(new Person("Jean", 25, Sex.FEMALE, "Jean@xyz.com"));
	}

	public static void main(String... arg) {

		Person.printPersonsOlderThan(persons, 20);

		Person.printPersonsWithinAgeRange(persons, 25, 30);

		Person.printPersons(persons, (Person p) -> p.getGender() == Sex.MALE && p.getAge() >= 15 && p.getAge() <= 25);

		Person.printPersonsWithPredicate(persons,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 15 && p.getAge() <= 25);

		System.out.println("\nprocessPersonsWithFunction: \n");
		Person.processPersonsWithFunction(persons,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 15 && p.getAge() <= 25, p -> p.getEmail(),
				email -> System.out.println(email));

		System.out.println("\nUsing stream(): \n");
		persons.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 15 && p.getAge() <= 55)
				.map(p -> p.getEmail()).forEach(email -> System.out.println(email));

		System.out.println("\nprocessElements using generics: \n");
		GenericTester.processElements(persons, p -> p.getGender() == Sex.MALE && p.getAge() >= 15 && p.getAge() <= 25,
				p -> p.getEmail(), email -> System.out.println(email));

	}

	public Person(String name, int age, Sex gender, String email) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}

	public enum Sex {
		MALE, FEMALE
	}

	private String name;
	private int age;
	private Sex gender;
	private String email;

	public int getAge() {
		return age;
	}

	public Sex getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public static void printPerson(Person p) {
		System.out.println(p);
	}

	public static void printPersonsOlderThan(List<Person> persons, int age) {
		System.out.println("printPersonsOlderThan: " + age + "\n");
		for (Person p : persons) {
			if (p.getAge() >= age) {
				printPerson(p);
			}
		}
	}

	public static void printPersonsWithinAgeRange(List<Person> persons, int low, int high) {
		System.out.println("\nprintPersonsWithinAgeRange: " + low + "-" + high + "\n");
		for (Person p : persons) {
			if (low <= p.getAge() && p.getAge() < high) {
				printPerson(p);
			}
		}
	}

	public static void printPersons(List<Person> persons, CheckPerson tester) {
		System.out.println("\nprintPersons using CheckPerson: \n");
		for (Person p : persons) {
			if (tester.test(p)) {
				printPerson(p);
			}
		}
	}

	public static void printPersonsWithPredicate(List<Person> persons, Predicate<Person> tester) {
		System.out.println("\nprintPersonsWithPredicate: \n");
		for (Person p : persons) {
			if (tester.test(p)) {
				printPerson(p);
			}
		}
	}

	public static void processPersonsWithFunction(List<Person> persons, Predicate<Person> tester,
			Function<Person, String> mapper, Consumer<String> block) {
		for (Person p : persons) {
			if (tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data);
			}
		}
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", email=" + email + "]";
	}

}

class GenericTester {

	public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper,
			Consumer<Y> block) {
		for (X p : source) {
			if (tester.test(p)) {
				Y data = mapper.apply(p);
				block.accept(data);
			}
		}
	}

}
