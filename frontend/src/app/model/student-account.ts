// Implementation on Student instance
import {Group} from "./group";
import {UserAccount} from "./UserAccount";

export class StudentAccount {
  studentId: number;
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
    clonedAcc.studentId = acc.studentId;
    clonedAcc.account = UserAccount.cloneAccount(acc.account);
    clonedAcc.group = Group.cloneGroup(acc.group);
    clonedAcc.address = acc.address;
    return clonedAcc;
  }
}
