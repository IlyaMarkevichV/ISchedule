package backend.service.impl;

import backend.entity.Account;
import backend.entity.Professor;
import backend.repository.AccountRepository;
import backend.repository.ProfessorRepository;
import backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository, AccountRepository accountRepository) {
        this.professorRepository = professorRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Professor saveProfessor(Professor entity) {

        if (entity.getId() == 0) {
            Optional<Account> accountOptional = accountRepository.getAccountByLogin(
                    entity.getAccount().getLogin());

            //check: is user with this login contains in db?
            if (accountOptional.isPresent()) {
                entity.setId(-1);
                entity.getAccount().setId(-1);
                return entity;
            }
        }

        return professorRepository.save(entity);
    }

    @Override
    public Optional<Professor> getProfessorByAccountId(String login) {
        return professorRepository.findProfessorByAccount_Login(login);
    }

    @Override
    public void deleteProfessor(Integer id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Page<Professor> getPage(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }
}
