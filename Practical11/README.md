# Practical 11 — Part A (Hour 1)

Java practice programs based on the provided practical sheet.

## Programs
1. Word frequency using `HashMap`; sort by frequency (highest first) using `Comparator`.
2. Unique visitors using `HashSet` and `LinkedHashSet`; count unique names and show first-seen order.
3. Generic `Box<T>` and `Pair<A,B>` with different types, without casts.
4. Bounded generic method `<N extends Number> double average(N[] values)` tested with `Integer[]`, `Double[]`, and `Long[]`.
5. Optional advanced program: generic stack backed by `ArrayList`, with safe empty-stack behavior.

## Requirements
- JDK 17 or later (the `Pair` record requires Java 16+).
- IntelliJ IDEA.
- No third-party dependencies.

## Run in IntelliJ IDEA
1. Extract the ZIP.
2. Open IntelliJ IDEA → **File → Open** → select the extracted `Practical11_PartA` folder.
3. Set Project SDK to JDK 17 or later.
4. Open `src/practical11/Practical11PartA.java`.
5. Click the green run icon beside `main()`, or right-click the file and choose **Run 'Practical11PartA.main()'**.
6. Use the menu in the Run console.

## Sample tests
- Word frequency: `Java is fun and Java is useful` → `java : 2`.
- Unique visitors: `Asha, Ravi, Asha, Neel, Ravi` → 3 unique visitors.
- Generic containers: displays a `Box<String>`, a `Box<Integer>`, and pairs of different types.
- Bounded average: displays averages for Integer, Double, and Long arrays.
- Generic stack: pushes two values, peeks, pops, and safely handles an empty stack.

This folder contains **Part A only**. The MiniBank project from Part B is not included.
