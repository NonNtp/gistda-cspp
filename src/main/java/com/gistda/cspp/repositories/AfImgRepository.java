package com.gistda.cspp.repositories;

import com.gistda.cspp.entities.AfImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AfImgRepository extends JpaRepository<AfImg , Long> {
    List<AfImg> findAllByDataDate (LocalDate dataDate) ;
    List<AfImg> findByLatitudeAndLongitude (Double latitude , Double longitude) ;
    @Query("SELECT dataDate FROM AfImg GROUP BY dataDate")
    List<LocalDate> findAllGroupByDataDate();


}
