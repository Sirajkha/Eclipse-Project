package com.irctc_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irctc_booking.entity.TrainRouteEntity;

@Repository
public interface TrainRouteRepository extends JpaRepository<TrainRouteEntity, Long> {

	@Query("SELECT src, dst FROM TrainRouteEntity src, TrainRouteEntity dst "+
	       "WHERE src.trainEntity = dst.trainEntity " + 
		   "AND src.stationEntity.code = :sourceCode "+
	       "AND dst.stationEntity.code = :destinationCode " +
		   "AND src.stopSequence < dst.stopSequence")
	List<Object[]> searchTrains(@Param("sourceCode") String sourceCode,@Param("destinationCode") String destinationCode); 
	//it will find train connecting two stations
}

//1. TrainRouteEntity src, TrainRouteEntity dst — two aliases for the same table, representing "the row where this train stops at the source" and "the row where it stops at the destination."
//2. src.trainEntity = dst.trainEntity — both rows must belong to the same train (otherwise you'd match station A on Train 1 and station B on unrelated Train 2).
//3. src.stationEntity.code = :sourceCode — this is navigating a relationship inside JPQL: src is a TrainRouteEntity, .stationEntity follows the @ManyToOne to get the StationEntity, .code gets its code field. JPQL lets you chain through relationships like this — no manual joins needed for simple navigation.
//4. src.stopSequence < dst.stopSequence — this is the actual "direction" check: source must come before destination in the train's stop order.
//5. :sourceCode / :destinationCode — named parameters, matched to method arguments either by matching parameter name (if compiled with -parameters) or by adding @Param("sourceCode") explicitly to be safe.
