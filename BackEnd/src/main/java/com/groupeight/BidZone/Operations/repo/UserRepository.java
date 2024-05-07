package com.groupeight.BidZone.Operations.repo;

import com.groupeight.BidZone.Operations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.id=:id")
    User findUserById(@Param("id") Integer id);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    //@Query("SELECT u FROM User u WHERE u.likedCategories like %:category%")
    //List<User> findUsersThatLikeACategory(@Param("category") String category);

    //@Query("SELECT COUNT(b) FROM Bid b WHERE b.user.id = :userId and b.timestamp > :date and b.listing.category = :category")
    //Integer getSimilarBidsCount(@Param("userId") Integer userId, @Param("date") Date date, @Param("category") String category);

}
