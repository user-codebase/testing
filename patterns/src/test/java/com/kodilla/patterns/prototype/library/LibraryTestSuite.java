package com.kodilla.patterns.prototype.library;

import com.kodilla.patterns.prototype.Board;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LibraryTestSuite {

    @Test
    public void testGetBooks(){
        //Given
        Library library = new Library("Library_1");
        Book book_1 = new Book("Book_1", "Author_1", LocalDate.of(2000, 5, 2));
        Book book_2 = new Book("Book_2", "Author_2", LocalDate.of(2005, 10, 12));
        Book book_3 = new Book("Book_3", "Author_3", LocalDate.of(2010, 12, 18));
        library.getBooks().add(book_1);
        library.getBooks().add(book_2);
        library.getBooks().add(book_3);

        //making a shallow copy of object library
        Library libraryShallowCopy = null;
        try {
            libraryShallowCopy = library.shallowCopy();
            libraryShallowCopy.setName("Library_shallow_copy");
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        //making a deep copy of object library
        Library libraryDeepCopy = null;
        try {
            libraryDeepCopy = library.deepCopy();
            libraryDeepCopy.setName("Library_deep_copy");
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        //When
        library.getBooks().remove(book_2);

        //Then
        System.out.println("Original library: " +  library);
        System.out.println("Shallow copy of library: " +  libraryShallowCopy);
        System.out.println("Deep copy of library: " +  libraryDeepCopy);
        assertEquals(2, library.getBooks().size());
        assertEquals(2, libraryShallowCopy.getBooks().size());
        assertEquals(3, libraryDeepCopy.getBooks().size());
        assertEquals(library.getBooks(), libraryShallowCopy.getBooks());
        assertNotEquals(library.getBooks(), libraryDeepCopy.getBooks());
    }
}
