package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Sequence;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper {

    Object getSequence(Sequence sequence);

    void updateSequence(Sequence parameterObject);
}
