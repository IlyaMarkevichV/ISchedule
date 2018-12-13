import {StudentAccount} from "./student-account";
import {Lesson} from "./lesson";

export class Attendance {
  id: number;
  student: StudentAccount;
  lesson: Lesson;
  date: string;
  status: number;
}
