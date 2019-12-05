package backend.repository;

import backend.entity.UniversityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityGroupRepository extends
        CrudRepository<UniversityGroup, Integer>,
        PagingAndSortingRepository<UniversityGroup, Integer> {
}
