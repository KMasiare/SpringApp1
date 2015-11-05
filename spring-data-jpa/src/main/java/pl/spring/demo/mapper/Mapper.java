package pl.spring.demo.mapper;

import pl.spring.demo.to.BookTo;
import pl.spring.demo.entities.BookEntity;

public interface Mapper {

	BookEntity map(BookTo bookTo);
	
	BookTo map(BookEntity bookEntity);
	
}
