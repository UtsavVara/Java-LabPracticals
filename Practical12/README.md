# Practical 12 — Part A (Hour 1)

Implements the Part A practice programs shown in the supplied Practical 12 sheet.

## Programs
1. Word frequency: split a paragraph, count words with `HashMap`, sort by frequency descending with `Comparator`.
2. Unique visitors: use `HashSet` for distinct count and `LinkedHashSet` to preserve first-seen order; display the first three distinct names.
3. Optional advanced task: create a player leaderboard using a `Map`, then sort by score descending and name ascending.

## Requirements
- JDK 17 or later
- IntelliJ IDEA
- No third-party libraries

## Run in IntelliJ
1. Extract the ZIP.
2. Choose **File → Open** and select the extracted `Practical12_PartA` folder.
3. Set Project SDK to JDK 17 or later.
4. Open `src/practical12/Practical12PartA.java`.
5. Click the green run icon beside `main()` or right-click the file and choose **Run 'Practical12PartA.main()'**.

## Sample tests
- Word frequency input: `java is fun and java is useful` → `java : 2`, `is : 2`, then remaining words alphabetically.
- Unique visitor input: `Asha, Ravi, Asha, Neel, Ravi` → 3 unique visitors; first-seen order is `[Asha, Ravi, Neel]`.
- Leaderboard: enter player names and scores; highest scores appear first.

This archive contains **Practical 12 Part A only**, not the MiniBank Part B project.
