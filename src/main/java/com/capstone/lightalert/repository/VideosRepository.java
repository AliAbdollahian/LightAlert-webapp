package com.capstone.lightalert.repository;

import com.capstone.lightalert.model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {
    List<Videos> findBySystemId(String systemId);
}
