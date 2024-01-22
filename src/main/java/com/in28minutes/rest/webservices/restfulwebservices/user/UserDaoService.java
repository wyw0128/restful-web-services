package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount, "Jack", LocalDate.now().minusYears(10)));
    }


    // retrieve all users
    public List<User> findAll() {
        return users;
    }

    // find one user
    public User findOne(int id) {
        /*
         functional programming:(Appendix)
         A Predicate is created to define a condition for filtering the User objects.
         <? super User> means that the Predicate will accept any type that is a superclass of User, including User itself.
        */
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        // .get() extracts the User object from the Optional, assuming that there is a match
        // (otherwise, it will throw a NoSuchElementException if the Optional is empty).
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    // delete a user
    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    // save a user
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
