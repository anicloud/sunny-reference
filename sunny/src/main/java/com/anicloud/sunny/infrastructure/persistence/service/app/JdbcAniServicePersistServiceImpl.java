package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */

public class JdbcAniServicePersistServiceImpl implements AniServicePersistService {

    @PersistenceContext
    private EntityManager em;

    private String jpql = " SELECT entity FROM AniServiceDao entity ";

    @Override
    public AniServiceDao findAniServiceInfo() {
        TypedQuery<AniServiceDao> query = em.createQuery(jpql, AniServiceDao.class);
        return query.getSingleResult();
    }

    @Override
    public AniServiceDao save(AniServiceDao aniServiceDao) {
        em.persist(aniServiceDao);
        return aniServiceDao;
    }

    @Override
    public AniServiceDao update(AniServiceDao aniServiceDao) {
        return em.merge(aniServiceDao);
    }
}
