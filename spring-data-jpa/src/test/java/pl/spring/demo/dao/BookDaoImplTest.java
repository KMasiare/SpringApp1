package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.BookTo;

public class BookDaoImplTest {

	@Test
	public void testFindBookByTitle() {
		// given
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		BookTo book = new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki");
		// when
		List<BookTo> books = bookDaoImpl.findBookByTitle("Pan Samochodzik i Fantomas");
		// then
		Assert.assertEquals(books.get(0), book);
	}
	
	@Test
	public void testFindBookByAuthor() {
		// given
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		BookTo book = new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki");
		// when
		List<BookTo> books = bookDaoImpl.findBooksByAuthor("Zbigniew Nienacki");
		// then
		Assert.assertEquals(books.get(0), book);
	}	

}

//ALL_BOOKS.add(mapper.map(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki")));
