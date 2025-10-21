package com.oiis.sso_starter.database.repositories.sso;

import com.oiis.sso_starter.database.entities.sso.BaseSsoEntityModel;
import com.oiis.sso_starter.database.repositories.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseSsoRepository<M extends BaseSsoEntityModel, ID> extends BaseRepository<M, ID> {

}