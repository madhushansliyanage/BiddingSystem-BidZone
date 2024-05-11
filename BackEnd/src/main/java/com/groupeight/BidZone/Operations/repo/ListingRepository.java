package com.groupeight.BidZone.Operations.repo;


import com.groupeight.BidZone.Operations.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {

    @Query(value = "SELECT * FROM Listing l WHERE l.ending >= :date ORDER BY l.ending ASC", nativeQuery = true)
    List<Listing> findListingsByDate(LocalDateTime date);


    /*@Query("SELECT l FROM Listing l WHERE l.ending > :date ORDER BY l.ending ASC")
    List<Listing> findActiveListings(@Param("date") Date date);

    @Query("SELECT l FROM Listing l WHERE l.id in :ids and l.ending > :date ORDER BY l.ending ASC")
    List<Listing> findActiveListingsByIds(@Param("date") Date date, @Param("ids") List<Integer> ids);

    @Query("SELECT l FROM Listing l WHERE l.ending <= :date ORDER BY l.ending DESC")
    List<Listing> findPastListings(@Param("date") Date date);

    @Query("SELECT l FROM Listing l WHERE l.id = :id")
    Listing findListingById(@Param("id") Integer id);

    @Query("SELECT DISTINCT l.category FROM Listing l")
    List<String> findCategories();

    @Query("SELECT l FROM Listing l WHERE l.ending > :date and l.category in :categories ORDER BY l.ending ASC")
    List<Listing> findUserPreferredActiveListings(@Param("date") Date date, @Param("categories") List<String> categories);

    @Query("SELECT l FROM Listing l WHERE l.ending > :date and l.category not in :categories ORDER BY l.ending ASC")
    List<Listing> findUserNotPreferredActiveListings(@Param("date") Date date, @Param("categories") List<String> categories);

    @Query("SELECT DISTINCT b.listing FROM Bid b WHERE b.listing.ending > :date and b.listing.category = :category and b.listing.id <> :listingId and b.user.id in :userIds")
    List<Listing> findSuggestedListings(@Param("date") Date date, @Param("category") String category, @Param("listingId") Integer listingId, @Param("userIds")List<Integer> userIds);
    */
}
