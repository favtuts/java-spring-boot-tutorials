package com.example.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<BookmarkInfo> findAllByOrderByCreatedAtDesc();

    Optional<BookmarkInfo> findBookmarkById(Long id);

    @Query(value = "SELECT NEXTVAL('public.bookmark_id_seq')", nativeQuery = true)
    Long getNextSequenceValue();
}