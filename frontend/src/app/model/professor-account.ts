import {UserAccount} from "./UserAccount";


export class ProfessorAccount {
  professorId: number;
  firstName: string;
  lastName: string;
  email: string;
  address: string;
  birthday: string;
  account: UserAccount;

  static cloneProfessorAccount(acc: ProfessorAccount): ProfessorAccount {
    let clonedAcc: ProfessorAccount = new ProfessorAccount();
    clonedAcc.professorId = acc.professorId;
    clonedAcc.firstName = acc.firstName;
    clonedAcc.lastName = acc.lastName;
    clonedAcc.email = acc.email;
    clonedAcc.address = acc.address;
    clonedAcc.birthday = acc.birthday;
    clonedAcc.account = acc.account;
    return clonedAcc;
  }
}
