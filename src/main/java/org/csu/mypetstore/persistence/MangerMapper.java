package org.csu.mypetstore.persistence;

import org.apache.ibatis.annotations.Param;
import org.csu.mypetstore.domain.Manger;
import org.springframework.stereotype.Repository;

@Repository
public interface MangerMapper {
    Manger login(@Param("account") String manger,@Param("password") String password);

    void changeInformation(Manger manger);

    void changePassword(@Param("account") String manger,@Param("password") String password);
}
