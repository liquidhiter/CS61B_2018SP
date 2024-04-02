# CS61B 2018 Spring Learning Notes - Chapter 9

## hw3 hashing
- typical implementation of `equal` method
```java
    /**
     * Compares this date to the specified date.
     *
     * @param  other the other date
     * @return {@code true} if this date equals {@code other}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }
```
- according to java specification
```markdown
Reflexive: x.equals(x) must be true for any non-null x.
Symmetric: x.equals(y) must be the same as y.equals(x) for any non-null x and y.
Transitive: if x.equals(y) and y.equals(z), then x.equals(z) for any non-null x, y, and z.
Consistent: x.equals(y) must return the same result if called multiple times, so long as the object referenced by x and y do not change.
Not-equal-to-null: x.equals(null) should be false for any non-null x.
```
- overwrite `hashCode` when `equal` is overwritten
```markdown
“Note that it is generally necessary to override the hashCodemethod whenever the equals method is overridden, so as to maintain the general contract for the hashCode method, which states that equal objects must have equal hash codes.”
```
    - reasoning
        - objects are stored in one `hashSet`
        - `contains` is used to check the existence of certain objects
        - which actually compares the equality of the objects
        - that's what described in the Java specification, "equal objects must have equal has codes"
    - NOTE:
        - `hashCode` returns the address of the objects by default

```markdown
If you’re getting a figure similar the first where everything is in two buckets, here’s why: Even though your hashCode is perfect, it’s always returning a multiple of 5. Try changing M to 9 in HashTableVisualizer, and you should see a nice spread of values. Understanding why this is happening is a great idea.
```
- 5k mod 10 always return 0 or 5, while 5k mod 9 returns number evenly

```java
    /* Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */
    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        for (int i = 0; i < 255; ++i) {
            List<Integer> deadParam = new ArrayList<>();
            deadParam.add(i);
            for (int j = 0; j < 10; ++j) {
                deadParam.add(0);
            }
            ComplexOomage coomage = new ComplexOomage(deadParam);
            deadlyList.add(coomage);
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }
```
- root cause:
    - 256 = 0b100000000, Integer in Java has limited range (signed)
    - hashCode is implemented by multiplying with 256 repeatedly, which always generate 0 when all non-zero bits are shifted out.
    - this happens to all the number with the same pattern
    - common trick to use prime numbers when calculating hashCode
