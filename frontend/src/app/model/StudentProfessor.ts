import {Group} from "./group";

export class StudentProfessor {
  id: number;
  firstName: string;
  lastName: string;
  birthday: string;
  group: Group;

  static clonedStudentProfessor(acc: StudentProfessor): StudentProfessor {
    let clonedAcc = new StudentProfessor();
    clonedAcc.id = acc.id;
    clonedAcc.firstName = acc.firstName;
    clonedAcc.lastName = acc.lastName;
    clonedAcc.birthday = acc.birthday;
    clonedAcc.group = Group.cloneGroup(acc.group);
    return clonedAcc;
  }
}
