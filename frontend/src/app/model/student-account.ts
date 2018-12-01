// Implementation on Student instance
import {Group} from "./group";
import {UserAccount} from "./UserAccount";

export class StudentAccount {
  id: number;
  account: UserAccount;
  group: Group;
  address: string;

  /**
   * Student cloning
   * @param acc - real student-tab account
   * @return student-tab prototype
   */
  static cloneStudentAccount(acc: StudentAccount): StudentAccount {
    let clonedAcc: StudentAccount = new StudentAccount();
    clonedAcc.id = acc.id;
    clonedAcc.account = UserAccount.cloneAccount(acc.account);
    clonedAcc.group = Group.cloneGroup(acc.group);
    clonedAcc.address = acc.address;
    return clonedAcc;
  }
}
