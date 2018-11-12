// Implementation on Student instance
import {Group} from "./group";

export class StudentAccount {
  studentId: number;
  group: Group;
  firstName: string;
  lastName: string;
  email: string;
  birthday: string;
  address: string;

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
    return prototype;
  }
}
