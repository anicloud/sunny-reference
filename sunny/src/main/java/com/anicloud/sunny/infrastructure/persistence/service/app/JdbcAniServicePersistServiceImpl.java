package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Component
public class JdbcAniServicePersistServiceImpl implements AniServicePersistService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AniServiceDao findAniServiceInfo() {
        AniServiceDao aniServiceDao = null;
        String jpql = " SELECT entity FROM AniServiceDao entity ";
        TypedQuery<AniServiceDao> query = em.createQuery(jpql, AniServiceDao.class);
        return query.getSingleResult();
    }

    @Override
    public AniServiceDao save(AniServiceDao aniServiceDao) {
        em.persist(aniServiceDao);
        String jpql = " SELECT entity FROM AniServiceDao entity ";
        TypedQuery<AniServiceDao> query = em.createQuery(jpql, AniServiceDao.class);
        if (query.getResultList().size() == 1) {
            return query.getSingleResult();
        }
        return null;
    }

    @Override
    public AniServiceDao update(AniServiceDao aniServiceDao) {
        em.merge(aniServiceDao);
        String jpql = " SELECT entity FROM AniServiceDao entity ";
        TypedQuery<AniServiceDao> query = em.createQuery(jpql, AniServiceDao.class);
        if (query.getResultList().size() == 1) {
            return query.getSingleResult();
        }
        return null;
    }
}
