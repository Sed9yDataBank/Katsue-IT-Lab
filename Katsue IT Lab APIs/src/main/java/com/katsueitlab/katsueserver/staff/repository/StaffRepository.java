package com.katsueitlab.katsueserver.staff.repository;

import com.katsueitlab.katsueserver.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {

}