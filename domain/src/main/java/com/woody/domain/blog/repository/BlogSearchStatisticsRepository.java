package com.woody.domain.blog.repository;

import com.woody.domain.blog.entity.BlogSearchStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by woody 2023.03.18
 * 블로그 검색 저장,수정,삭제를 위한 Repository
 * */
public interface BlogSearchStatisticsRepository extends JpaRepository<BlogSearchStatistics, Long> {

    Optional<BlogSearchStatistics> findByKeyword(String keyword);
}
