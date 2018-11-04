import {Faculty} from "./faculty";

export class Group {
  groupId: number;
  faculty: Faculty;
  grade: number;
  date: string;

  static cloneGroup(group: Group): Group {
    let prototype = new Group();
    prototype.groupId = group.groupId;
    prototype.faculty = group.faculty;
    prototype.grade = group.grade;
    prototype.date = group.date;
    return prototype;
  }
}
