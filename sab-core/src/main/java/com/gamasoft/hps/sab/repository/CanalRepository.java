package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Canal;

public interface CanalRepository extends Repository<Canal> {

	public List<Canal> getAll();

}
