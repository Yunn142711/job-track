package com.jobtrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobtrack.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}