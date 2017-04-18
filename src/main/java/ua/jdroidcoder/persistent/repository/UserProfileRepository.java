package ua.jdroidcoder.persistent.repository;

import org.springframework.data.repository.CrudRepository;
import ua.jdroidcoder.persistent.entity.UserProfileEntity;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public interface UserProfileRepository extends CrudRepository<UserProfileEntity, Long> {
    UserProfileEntity findUserProfileByEmail(String email);

    UserProfileEntity findUserProfileByPhone(String phone);
}
