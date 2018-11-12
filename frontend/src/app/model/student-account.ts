// Implementation on Student instance
import {Group} from "./group";
import {UserAccount} from "./UserAccount";

export class StudentAccount {
  studentId: number;
  group: Group;
  firstName: string;
  lastName: string;
  email: string;
  birthday: string;
  address: string;
  account: UserAccount;

  /**
   * Student cloning
   * @param studentAccount - real student-tab account
   * @return student-tab prototype
   */
  static cloneStudentAccount(studentAccount: StudentAccount): StudentAccount {
    let prototype: StudentAccount = new StudentAccount();
    prototype.studentId = studentAccount.studentId;
    prototype.group = studentAccount.group;
    prototype.firstName = studentAccount.firstName;
    prototype.lastName = studentAccount.lastName;
    prototype.email = studentAccount.email;
    prototype.birthday = studentAccount.birthday;
    prototype.address = studentAccount.address;
    prototype.account = studentAccount.account;
    return prototype;
  }
}
