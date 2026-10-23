# Practical 13 — Part A (Hour 1)

Java Generics practice programs based on the supplied practical sheet.

## Programs
1. Generic `Box<T>` and `Pair<A,B>` with multiple types, no unsafe casts, and a method demonstrating the first element.
2. Bounded generic method `<N extends Number> double average(N[] values)` tested using `Integer[]`, `Double[]`, and `Long[]`.
3. Optional advanced exercise: generic stack backed by `ArrayList`, with safe behavior when empty.

## Requirements
- JDK 17 or later.
- IntelliJ IDEA.
- No third-party dependencies.

## Run in IntelliJ IDEA
1. Extract the ZIP.
2. Select **File → Open** and choose the extracted `Practical13_PartA` folder.
3. Set Project SDK to JDK 17 or later.
4. Open `src/practical13/Practical13PartA.java`.
5. Click the green run icon beside `main()` or right-click the file and choose **Run 'Practical13PartA.main()'**.

## Test
- Option 1 prints Box<String>, Box<Integer>, Box<Double>, and pairs of different types.
- Option 2 prints averages for Integer, Double, and Long arrays.
- Option 3 demonstrates push, peek, pop, and safe pop on an empty stack.

This archive contains **Practical 13 Part A only**. The MiniBank Part B project is not included.
