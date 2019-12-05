package backend.service;

import backend.entity.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProfessorService {
    Professor saveProfessor(Professor entity);

    Optional<Professor> getProfessorByAccountId(String login);

    void deleteProfessor(Integer id);

    Page<Professor> getPage(Pageable pageable);
}
