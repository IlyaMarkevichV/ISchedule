// Implementation on Student instance
export class StudentAccount {
  studentId: number;
  groupId: number;
  firstName: string;
  lastName: string;
  email: string;
  birthday: string;
  address: string;

  /**
   * Student cloning
   * @param acc - real student account
   * @return student prototype
   */
  static cloneStudentAccount(acc: StudentAccount): StudentAccount {
    let clonedAcc: StudentAccount = new StudentAccount();
    clonedAcc.studentId = acc.studentId;
    clonedAcc.groupId = acc.groupId;
    clonedAcc.firstName = acc.firstName;
    clonedAcc.lastName = acc.lastName;
    clonedAcc.email = acc.email;
    clonedAcc.birthday = acc.birthday;
    clonedAcc.address = acc.address;
    return clonedAcc;
  }
}
