package com.oiis.sso_starter.database.repositories.sso;

import com.oiis.sso_starter.database.entities.sso.BaseSsoEntityModel;
import com.oiis.sso_starter.database.repositories.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * base sso repository
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@NoRepositoryBean
public interface BaseSsoRepository<M extends BaseSsoEntityModel, ID> extends BaseRepository<M, ID> {

}