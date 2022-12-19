package com.team2.crowdfunding.repository;

import com.team2.crowdfunding.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Project, Integer> {

    // 좋아요 수 조회
    @Query("SELECT p.likes FROM Project p WHERE p.id = :id")
    int findLikesById(@Param("id") int id);

    // 총 좋아요 수 조회
    @Query("SELECT p.totalLikes FROM Project p WHERE p.id = :id")
    int findTotalLikesById(@Param("id") int id);

    // 좋아요 수 증가
    @Modifying
    @Query("UPDATE Project p SET p.likes = p.likes + 1, p.totalLikes = p.totalLikes + 1 WHERE p.id = :id")
    void likeProject(@Param("id") int id);
}