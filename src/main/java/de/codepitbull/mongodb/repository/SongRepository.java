package de.codepitbull.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jochen Mader
 */
public interface SongRepository extends MongoRepository<Song, String>, PagingAndSortingRepository<Song, String> {
}
