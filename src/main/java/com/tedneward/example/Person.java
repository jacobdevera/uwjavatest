package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private static int counter;
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    counter++;
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person a, Person b) {
      return a.age < b.age ? -1 : a.age == b.age ? 0 : 1;
    }
  }

  public static int count() {
    return counter;
  }

  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public double getSalary() {
    return this.salary;
  }

  /* CHECK */
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person) other;
      return (name.equals(p.name) && age == p.age);
    }
    return false;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public int compareTo(Person other) {
    return this.salary > other.salary ? -1 : this.salary == other.salary ? 0 : 1;
  }

  public static List<Person> getNewardFamily() {
    List<Person> result = new ArrayList<Person>();
    result.add(new Person("Ted", 41, 250000));
    result.add(new Person("Charlotte", 43, 150000));
    result.add(new Person("Michael", 22, 10000));
    result.add(new Person("Matthew", 15, 0));
    return result;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
