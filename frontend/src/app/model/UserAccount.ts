import {Role} from "./role";

export class UserAccount {
  accountId: number;
  login: string;
  password: string;
  role: Role;
  firstName: string;
  lastName: string;
  birthday: string;

  static cloneAccount(acc: UserAccount): UserAccount {
    let clonedAcc = new UserAccount();
    clonedAcc.accountId = acc.accountId;
    clonedAcc.login = acc.login;
    clonedAcc.password = acc.password;
    clonedAcc.role = Role.cloneRole(acc.role);
    clonedAcc.firstName = acc.firstName;
    clonedAcc.lastName = acc.lastName;
    clonedAcc.birthday = acc.birthday;
    return clonedAcc;
  }
}
