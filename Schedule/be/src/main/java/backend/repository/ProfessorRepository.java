package backend.repository;

import backend.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends
        CrudRepository<Professor, Integer>,
        PagingAndSortingRepository<Professor, Integer> {

    Optional<Professor> findProfessorByAccount_Login(String login);
}
