package ua.jdroidcoder.persistent.repository;

import org.springframework.data.repository.CrudRepository;
import ua.jdroidcoder.persistent.entity.UserEntity;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
}
