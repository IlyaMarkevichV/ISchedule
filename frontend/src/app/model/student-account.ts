// Implementation on Student instance
import {Group} from "./group";
import {UserAccount} from "./UserAccount";

export class StudentAccount {
  tableId: number;
  user: UserAccount;
  group: Group;

  /**
   * Student cloning
   * @param acc - real student-tab account
   * @return student-tab prototype
   */
  static cloneStudentAccount(acc: StudentAccount): StudentAccount {
    let clonedAcc: StudentAccount = new StudentAccount();
    clonedAcc.tableId = acc.tableId;
    clonedAcc.user = acc.user;
    clonedAcc.group = acc.group;
    return clonedAcc;
  }
}
