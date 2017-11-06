package com.twx.db;

import com.twx.entity.SVLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by twx on 2017/11/6.
 */
public interface MyJpaRepository extends JpaRepository<SVLEntity,Integer> {
    List<SVLEntity> findByContainerNo(String containerNo);
}
