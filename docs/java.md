# Array and List (java.util.*)

- **Declaration** is locating the memory address (but has no value is stored)
- **Instantiation** is assigning the value to that location
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/index.html)

**Reference**

- https://www.toppr.com/guides/computer-science/introduction-to-c/data-types-variables-and-constants/declaration-of-variables/

## Array
- common java (java.util.*) has no `Array` class, but has only `Arrays` class that have common method for array operation.
- the primitive array in java is `[]`
- can store both **primitive** and **object** with fixed size
- syntax
    ```java
    // Declaration
    dataType[] arr; // or  
    dataType []arr; // or  
    dataType arr[];
  
    // Instantiation
    arr = new dataType[size];
  
  // Assign value to element
  Course courses = { new Course("Course 1"), new Course("Course 2") };
  ```
- array variable is also **java object**, so to create new array we must use keyword `new` [Ref](http://underpop.online.fr/j/java/help/declaring-and-creating-arrays-arrays.html.gz)
- other class like `Arrays`, `String`, `Integer`, and so on are used for interact each type easily

**Reference**
- https://www.javatpoint.com/array-in-java

## List

- store only `object` (can also pass primitive, but it will convert to object inside)

![collections in java](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20211209124013/Collections-in-Java.png)

`List` is child interface of `collection` interface that is implemented by below 4 classes:
```java
List a = new ArrayList();
List b = new LinkedList();
List c = new Vector(); 
List d = new Stack(); 
```
To **Declare and Initiate element** use `{{}}` with call function `add()`
```java
List<Course> courses = new ArrayList<Course>(){{ 
    add(new Course("course 1"));
    add(new Course("course 2"));
    add(new Course("course 3"));
}};

// or use asList() in Arrays class
List<Course> courses2 = Arrays.asList(
        new Course("course 1"),
        new Course("course 2"),
        new Course("course 3")
);
```

**References**
- https://www.geeksforgeeks.org/initializing-a-list-in-java