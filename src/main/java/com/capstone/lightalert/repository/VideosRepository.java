package com.capstone.lightalert.repository;

import com.capstone.lightalert.model.Videos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends CrudRepository<Videos, Long> {

    List<Videos> findByUser_SystemId(String systemId);
}


