package com.gm.demo.shard.dao.repository;

import com.gm.demo.shard.dao.entity.Test;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Jason
 */
public interface TestRepository extends CrudRepository<Test, Long> {
}