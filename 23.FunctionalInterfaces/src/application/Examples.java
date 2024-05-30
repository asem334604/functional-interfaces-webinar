package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Examples {

	public static void main(String[] args) {

//		============================================================================

		// Consumer<T, U>
		// 1. receives 1 parameter
		// 2. returns nothing(void)

//		============================================================================

		Consumer<String> consumer = new Consumer<>() {

			@Override
			public void accept(String t) {
				// change of object
				// print of console
				// actions which doesn't return anything(void)
				System.out.println("String input:" + t);
			}

		};

		List<String> values1 = List.of("Apple", "Pinapple", "Milk");

//		for (String s : values1) {
//			System.out.println("String input:" + s);
//		}

//		values1.forEach(consumer);

		values1.forEach(new Consumer<String>() { // s -> System.out.println("String input:" + s)

			@Override
			public void accept(String t) {
				// change of object
				// print of console
				// actions which doesn't return anything(void)
				System.out.println("String input:" + t);
			}

		});

//		============================================================================

		// BiConsumer<T, U>
		// 1. receives 2 parameters
		// 2. returns nothing(void)

//		============================================================================

		Map<String, Integer> map1 = Map.of("Jan", 1, "Feb", 2, "Mar", 3);

		BiConsumer<String, Integer> biConsumer = new BiConsumer<>() {

			@Override
			public void accept(String t, Integer u) {
				System.out.println((u % 2 == 0 ? "I like: " : "") + t);
			}
		};

		map1.forEach(biConsumer);

		List<String> favouriteMonths = new ArrayList<>();

		map1.forEach(new BiConsumer<String, Integer>() {
			@Override
			public void accept(String t, Integer u) {
				if (u % 2 == 0)
					favouriteMonths.add(t);
			}
		});

		System.out.println("\n\n\n");

		favouriteMonths.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}

		});

//		============================================================================

		// Supplier
		// 1. doesn't receives parameters
		// 2. returns something
		
//		============================================================================

		Supplier<Student> studentGenerator = new Supplier<>() {
			private static int ID_GENERATOR = 1;

			@Override
			public Student get() {
				return new Student(ID_GENERATOR++);
			}

		};

		List<Student> students = generateNewObjects(studentGenerator, 10);

		System.out.println("\n");

		students.forEach(new Consumer<Student>() {

			@Override
			public void accept(Student t) {
				System.out.println(t);
			}

		});

		List<String> strings = generateNewObjects(new Supplier<>() {
			private static final String DEFAULT = "Hello";
			private static int counter = 1;

			@Override
			public String get() {
				return counter++ % 2 == 1 ? DEFAULT.concat(" World!") : DEFAULT;
			}

		}, 15);

		System.out.println("\n\n");

		strings.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}

		});

	}

	public static <T> List<T> generateNewObjects(Supplier<T> generator, int amount) {
		List<T> result = new ArrayList<>();
		try {
			for (int i = 1; i <= amount; i++)
				result.add(generator.get());
		} catch (Exception e) {
			result.clear();
		}
		return result;
	}
	
//	============================================================================
	
	//Function/BiFunction
	//1. receive parameters
	//2. return something
	
	//uses : conversion/transformation, processing functionality
	
	Function<String, Integer> function = new Function<>() {

		@Override
		public Integer apply(String t) {
			if (t != null) {
				return t.length();
			} else {
				return null;
			}
		}
		
	};
	
	BiFunction<String, String, Integer> biFunction = new BiFunction<>() {

		@Override
		public Integer apply(String t, String u) {
			if (t != null && u != null) {
				return t.length() + u.length();
			} else {
				return null;
			}
		}
		
	};
			
	
	
	
//	============================================================================
	
	//UnaryOperator
	//1. receives 1 parameter
	//2. return result of the same type as input parameter
	
	UnaryOperator<Integer> unaryOperator = new UnaryOperator<>() {

		@Override
		public Integer apply(Integer t) {
			return t + 3;
		}
		
	};
	
	//BinaryOperator
	//1. receives 2 parameters of the same type
	//2. return result of the same type as input parameters
	
	
	BinaryOperator<Integer> binaryOperator = new BinaryOperator<>() {

		@Override
		public Integer apply(Integer t, Integer u) {
			return (t + u) / 3;
		}
		
	};

}
