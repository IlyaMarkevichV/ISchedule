import {UserAccount} from "./UserAccount";


export class ProfessorAccount extends UserAccount {

  static cloneProfessorAccount(acc: ProfessorAccount): ProfessorAccount {
    let clonedAcc = new UserAccount();
    clonedAcc.accountId = acc.accountId;
    clonedAcc.email = acc.email;
    clonedAcc.password = acc.password;
    clonedAcc.role = acc.role;
    clonedAcc.firstName = acc.firstName;
    clonedAcc.lastName = acc.lastName;
    clonedAcc.birthday = acc.birthday;
    return clonedAcc;
  }
}
