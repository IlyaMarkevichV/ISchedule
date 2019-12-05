package backend.service.impl;

import backend.entity.Account;
import backend.entity.Student;
import backend.repository.AccountRepository;
import backend.repository.StudentRepository;
import backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private AccountRepository accountRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, AccountRepository accountRepository) {
        this.studentRepository = studentRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Student saveStudent(Student entity) {
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
        return studentRepository.save(entity);
    }

    @Override
    public Optional<Student> getStudentByLogin(String login) {
        return studentRepository.getStudentByAccount_Login(login);
    }

    @Override
    public void deleteStudents(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> getPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
