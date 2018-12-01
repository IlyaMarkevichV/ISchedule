import {Faculty} from "./faculty";

export class Group {
  id: number;
  faculty: Faculty;
  speciality: string;
  grade: number;
  graduation: string;

  static cloneGroup(group: Group): Group {
    if (!group)
      return null;
    let clonedAcc = new Group();
    clonedAcc.id = group.id;
    clonedAcc.faculty = Faculty.cloneFaculty(group.faculty);
    clonedAcc.speciality = group.speciality;
    clonedAcc.grade = group.grade;
    clonedAcc.graduation = group.graduation;
    return clonedAcc;
  }
}
