package com.demo.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.tms.model.Trustee;

@Repository
public interface TrusteeRepository extends JpaRepository<Trustee, Long>{

}
