package com.groupeight.BidZone.Operations.repo;

import com.groupeight.BidZone.Operations.dto.BidListingDTO;
import com.groupeight.BidZone.Operations.entity.Bid;
import com.groupeight.BidZone.Operations.entity.Listing;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

    @Query(value = "SELECT * FROM Bid WHERE user_id=?1",nativeQuery = true)
    List<Bid> findBidByUserId(int userId);

    @Query(value = "SELECT * FROM Bid WHERE listing_id=?1",nativeQuery = true)
    List<Bid> findBidByListingId(int listingId);

    @Query(value ="SELECT b.id AS bid_id, b.listing_id AS listing_Id, b.user_id AS user_id, " +
            "l.name AS listing_name, l.description AS listing_description, " +
            "l.category AS listing_category, l.ending AS listing_ending, " +
            "b.timestamp AS bid_timestamp, b.price AS bid_price " +
            "FROM Bid b " +
            "INNER JOIN Listing l ON b.listing_id = l.id " +
            "WHERE b.user_id = :userId " +
            "AND l.ending < :givenDate " +
            "AND b.status = 'pending' " +
            "AND (b.listing_id, b.price) IN " +
            "(SELECT max_bids.listing_id, MAX(max_bids.max_price) " +
            " FROM (SELECT listing_id, MAX(price) AS max_price " +
            "       FROM Bid " +
            "       GROUP BY listing_id) AS max_bids " +
            " GROUP BY max_bids.listing_id)",nativeQuery = true)
    List<Object[]> findHighestBidsForUserAndEndingBeforeDate(
             int userId,
             LocalDateTime givenDate);



    @Query(value ="SELECT b.id AS bid_id, b.listing_id AS listing_Id, b.user_id AS user_id, " +
            "l.name AS listing_name, l.description AS listing_description, " +
            "l.category AS listing_category, l.ending AS listing_ending, " +
            "b.timestamp AS bid_timestamp, b.price AS bid_price " +
            "FROM Bid b " +
            "INNER JOIN Listing l ON b.listing_id = l.id " +
            "WHERE b.user_id = :userId " +
            "AND b.status = 'pending' " +
            "AND (b.listing_id, b.price) IN " +
            "(SELECT max_bids.listing_id, MAX(max_bids.max_price) " +
            " FROM (SELECT listing_id, MAX(price) AS max_price " +
            "       FROM Bid " +
            "       GROUP BY listing_id) AS max_bids " +
            " GROUP BY max_bids.listing_id)",nativeQuery = true)
    List<Object[]> findHighestBidsForUserAndEndingBeforeDate1111(
             int userId);


    @Transactional
    @Modifying
    @Query("UPDATE Bid b SET b.status = 'complete' WHERE b.id = :bidId")
    void updateBidStatusToComplete(int bidId);

    /*@Query("SELECT b FROM Bid b WHERE b.user.id=:userId")
    List<Bid> findUserActiveBids(@Param("userId")Integer userId);

    @Query("SELECT b.listing.category, COUNT(b) FROM Bid b WHERE b.user.age >= :min and b.user.age <= :max GROUP BY b.listing.category")
    List<Object[]> retrieveAgeRangeStatisticsPerCategory(@Param("min")Integer min, @Param("max")Integer max);

    @Query("SELECT b.user.age, COUNT(b) FROM Bid b WHERE b.listing.category = :category GROUP BY b.user.age")
    List<Object[]> retrieveCategoryStatisticsPerAgeRange(@Param("category")String category);

    @Query("SELECT b.listing.category, COUNT(b) FROM Bid b WHERE b.user.gender = :gender GROUP BY b.listing.category")
    List<Object[]> retrieveGenderStatisticsPerCategory(@Param("gender")String gender);

    @Query("SELECT b.user.gender, COUNT(b) FROM Bid b WHERE b.listing.category = :category GROUP BY b.user.gender")
    List<Object[]> retrieveCategoryStatisticsPerGender(@Param("category")String category);

    @Query("SELECT b.listing.category, COUNT(b) FROM Bid b WHERE b.user.gender = :gender and b.user.age >= :min and b.user.age <= :max GROUP BY b.listing.category")
    List<Object[]> retrieveCombinationStatisticsPerCategory(@Param("gender")String gender, @Param("min")Integer min, @Param("max")Integer max);

    @Query("SELECT b.user.gender, b.user.age, COUNT(b) FROM Bid b WHERE b.listing.category = :category GROUP BY b.user.gender, b.user.age")
    List<Object[]> retrieveCategoryStatisticsPerCombination(@Param("category")String category);*/

}
