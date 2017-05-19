import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterDemo {

	public static void main(String args[]) {

		List<Computer> computers = new ArrayList<>();
		computers.add(new Computer("Blue", new BigDecimal("10000"), 17f, "Inspiron"));
		computers.add(new Computer("Black", new BigDecimal("20000"), 18f, "XPS"));
		computers.add(new Computer("Pink", new BigDecimal("15000"), 18f, "Inspiron"));
		computers.add(new Computer("Pink", new BigDecimal("30000"), 17f, "Alienware"));
		computers.add(new Computer("Blue", new BigDecimal("15000"), 21f, "Inspiron"));

		List<Computer> filteredComps = ComputerSelector.getFilteredComputers(computers, Optional.empty(),
				Optional.ofNullable(new BigDecimal("15000")), Optional.ofNullable(new Boolean(true)), Optional.empty());

		System.out.println(filteredComps);
	}
}

class ComputerSelector {

	public static List<Computer> getFilteredComputers(List<Computer> inventory, Optional<String> color,
			Optional<BigDecimal> price, Optional<Boolean> useGreaterThan, Optional<Float> screenSize) {
		List<Predicate<Computer>> predicates = new ArrayList<>();
		if (color.isPresent()) {
			predicates.add(c -> c.getColor().equals(color.get()));
		}
		if (price.isPresent() && useGreaterThan.isPresent()) {
			if (useGreaterThan.get()) {
				predicates.add(c -> c.getPrice().compareTo(price.get()) >= 0);
			} else {
				predicates.add(c -> c.getPrice().compareTo(price.get()) <= 0);
			}
		}
		if (screenSize.isPresent()) {
			predicates.add(c -> c.getSize().compareTo(screenSize.get()) == 0);
		}
		Predicate<Computer> compositePredicate = predicates.stream().reduce(c -> true, Predicate::and);
		List<Computer> filteredComputers = inventory.stream().filter(compositePredicate).collect(Collectors.toList());
		return filteredComputers;
	}
}

class Computer {
	private String color;
	private BigDecimal price;
	private Float size;
	private String model;

	public Computer(String color, BigDecimal price, float size, String model) {
		super();
		this.color = color;
		this.price = price;
		this.size = size;
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Computer [color=" + color + ", price=" + price + ", size=" + size + ", model=" + model + "]";
	}

}
