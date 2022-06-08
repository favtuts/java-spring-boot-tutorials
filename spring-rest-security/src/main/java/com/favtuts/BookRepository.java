package com.favtuts;

import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data magic :)
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
