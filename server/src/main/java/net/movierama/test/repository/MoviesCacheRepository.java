package net.movierama.test.repository;

import net.movierama.test.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Repository
public class MoviesCacheRepository {

    private static final Logger logger = LoggerFactory.getLogger(MoviesCacheRepository.class);

    @Autowired
    private CacheManager cacheManager;

    /**
     * Caches a movie using cacheManager with TTL one week.
     *
     * @param title The cache key.
     * @param title The cache value.
     */
    public void cacheMovie(final String title, final Movie movie) {
        final Cache cache = cacheManager.getCache("movieFindCache");
        cache.put(title.toLowerCase(), movie);
    }

    /**
     * Retrieves a Movie from the cache system or null in there is no such an entry.
     *
     * @param title The cache key to search for.
     * @return The Movie found in cache or null otherwise.
     */
    public Movie findByTitle(final String title) {
        final Cache cache = cacheManager.getCache("movieFindCache");
        final Cache.ValueWrapper mValueWrapper = cache.get(title.toLowerCase());

        Movie movie = null;
        if (mValueWrapper != null) {
            movie = (Movie) mValueWrapper.get();
            logger.debug("Found in cache title[{}]", title);
        }
        return movie;
    }
}
