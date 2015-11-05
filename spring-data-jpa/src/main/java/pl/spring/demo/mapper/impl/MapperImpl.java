package pl.spring.demo.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entities.BookEntity;
import pl.spring.demo.mapper.Mapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Component
public class MapperImpl implements Mapper {

	@Override
	public BookEntity map(BookTo bookTo) {
		BookEntity bookEntity = new BookEntity();
		List<AuthorTo> authors = new ArrayList<AuthorTo>();
		String[] authorsString = bookTo.getAuthors().split(" ");
		int counter = 0;
		AuthorTo author = new AuthorTo();
		
		for (String s : authorsString) {
			if((counter % 2) == 0) {
				author.setFirstName(s);
			} else {
				author.setLastName(s);
				Sequence seq = new Sequence();
				Long id = seq.nextValue(authors);
				author.setId(id);
				authors.add(author);
				author = new AuthorTo();
			}
			counter++;
		}
		
		bookEntity.setId(bookTo.getId());
		bookEntity.setTitle(bookTo.getTitle());
		bookEntity.setAuthors(authors);
		
		return bookEntity;
	}

	@Override
	public BookTo map(BookEntity bookEntity) {
		BookTo bookTo = new BookTo();
		List<String> authorsNames = new ArrayList<String>();
		
		for (AuthorTo author : bookEntity.getAuthors()) {
			authorsNames.add(author.getFirstName() + " " + author.getLastName());
		}
		
		String authors = String.join(" ", authorsNames);
		
		bookTo.setId(bookEntity.getId());
		bookTo.setTitle(bookEntity.getTitle());
		bookTo.setAuthors(authors);
		
		return bookTo;
	}
	
	

}
