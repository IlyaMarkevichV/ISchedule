export class ProfessorAccount {
  professorId: number;
  firstName: string;
  lastName: string;
  email: string;
  address: string;
  birthday: string;

  static cloneProfessorAccount(acc: ProfessorAccount): ProfessorAccount {
    let clonedAcc: ProfessorAccount = new ProfessorAccount();
    clonedAcc.professorId = acc.professorId;
    clonedAcc.firstName = acc.firstName;
    clonedAcc.lastName = acc.lastName;
    clonedAcc.email = acc.email;
    clonedAcc.address = acc.address;
    clonedAcc.birthday = acc.birthday;
    return clonedAcc;
  }
}
