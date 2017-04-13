package ua.jdroidcoder.persistent.repository;

import org.springframework.data.repository.CrudRepository;
import ua.jdroidcoder.persistent.entity.UserCoordinateEntity;

/**
 * Created by jdroidcoder on 10.04.17.
 */
public interface UserCoordinateRepository extends CrudRepository<UserCoordinateEntity, Long> {
    UserCoordinateEntity findUserCoordinateByUserPhone(String userPhone);
}
