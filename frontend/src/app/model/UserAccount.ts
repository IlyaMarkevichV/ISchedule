import {StudentProfessor} from "./StudentProfessor";

export class UserAccount {
  accountId: number;
  login: string;
  password: string;
  role: string;
  studentProfessor: StudentProfessor;

  static cloneAccount(acc: UserAccount): UserAccount {
    let clonedAcc = new UserAccount();
    clonedAcc.accountId = acc.accountId;
    clonedAcc.login = acc.login;
    clonedAcc.password = acc.password;
    clonedAcc.role = acc.role;
    clonedAcc.studentProfessor = StudentProfessor.clonedStudentProfessor(acc.studentProfessor);
    return clonedAcc;
  }
}
