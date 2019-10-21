package com.rovitapps.google.books.integration.api.repository;

import com.rovitapps.google.books.integration.api.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{$or: [{'title': { $regex: '?0', $options: 'i'}}, " +
            "{'libraryCode': { $regex: '?0', $options: 'i'}}, " +
            "{'_id': '?0'}, " +
            "{'authors': { $regex: '?0', $options: 'i'}}]}")
    Page<Book> findAllByCriteria(Pageable pageable, String q);

    Book findByTitle(String Title);

    Book findById(String id, Class<Book> bookClass);
}
