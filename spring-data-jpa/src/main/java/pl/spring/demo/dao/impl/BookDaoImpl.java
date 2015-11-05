package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NewIdAnnotation;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entities.BookEntity;
import pl.spring.demo.mapper.Mapper;
import pl.spring.demo.mapper.impl.MapperImpl;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;

public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	public Set<BookEntity> getALL_BOOKS() {
		return ALL_BOOKS;
	}

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookTo> findAll() {
		List<BookTo> books = new ArrayList<BookTo>();
		MapperImpl mapper = new MapperImpl();
		for (BookEntity book : ALL_BOOKS) {
			books.add(mapper.map(book));
		}
		return books;
	}

	@Override
	public List<BookTo> findBookByTitle(String title) {
		List<BookTo> books = new ArrayList<BookTo>();
		Mapper mapper = new MapperImpl();

		for (BookEntity book : ALL_BOOKS) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				books.add(mapper.map(book));
			}
		}
		return books;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		List<BookTo> books = new ArrayList<BookTo>();
		Mapper mapper = new MapperImpl();
		
		for (BookEntity book : ALL_BOOKS) {
			BookTo bookTo = mapper.map(book);
			if (bookTo.getAuthors().equals(author)) {
				books.add(bookTo);
			}
		}
		return books;
	}

	@Override
	@NullableId
	@NewIdAnnotation
	public BookTo save(BookTo book) {
		MapperImpl mapper = new MapperImpl();
		ALL_BOOKS.add(mapper.map(book));
		return book;
	}

	private void addTestBooks() {
		Mapper mapper = new MapperImpl();
		ALL_BOOKS.add(mapper.map(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir")));
		ALL_BOOKS.add(mapper.map(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska")));
		ALL_BOOKS.add(mapper.map(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski")));
		ALL_BOOKS.add(mapper.map(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski")));
		ALL_BOOKS.add(mapper.map(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki")));
		ALL_BOOKS.add(mapper.map(new BookTo(6L, "Zemsta", "Aleksander Fredro")));
	}
}
