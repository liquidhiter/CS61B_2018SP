# CS61B 2018 Spring Learning Notes - Chapter 2

## Topic: data structure of List

### Objects
```java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
b.weight = 5;

int x = 5;
int y;
y = x;
x = 2;
```
- `b` and `a` points to the same instance, i.e. same memory address
- primitive types: values are directly copied

### Variable and bits representation in memory
`In addition to setting aside memory, the Java interpreter also creates an entry in an internal table that maps each variable name to the location of the first bit in the box.`
- internal lookup table and data structure used to locate the memory efficiently


### Variables can be used until they are initialized

### Primitive types and reference types
- variable stores the address of the instance of any reference type
```java
Walrus someWalrus;
someWalrus = new Walrus(1000, 8.3);
```
![refernce type](./../asset/reference_type.png "Reference Type")


### Parameter passing
- always **pass by value** in Java 


### IterativeSize
- `this` can't be re-assigned


### Nested Classes
- `A simple rule of thumb is that if you don't use any instance members of the outer class, make the nested class static.`


### Empty List
- `addLast` method doesn't work on empty lists
- `getFirst` method also doesn't work on empty lists


### Special Cases
- Try to avoid special cases as much as possible by having a good design of code