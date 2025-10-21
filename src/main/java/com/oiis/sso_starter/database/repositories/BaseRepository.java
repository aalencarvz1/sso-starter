package com.oiis.sso_starter.database.repositories;

import com.oiis.sso_starter.database.entities.BaseEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<M extends BaseEntityModel, ID> extends JpaRepository<M, ID>, JpaSpecificationExecutor<M> {

}