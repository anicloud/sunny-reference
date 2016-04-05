package com.anicloud.sunny.infrastructure.persistence.repository.user;

import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface UserRepository extends JpaRepository<UserDao, Long> {
    public UserDao findByHashUserId(Long hashUserId);
    public UserDao findByEmail(String email);
}
