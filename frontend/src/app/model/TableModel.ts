import {StudentAccount} from "./student-account";
import {Group} from "./group";
import {Faculty} from "./faculty";
import {ProfessorAccount} from "./professor-account";
import {Role} from "./role";
import {Lesson} from "./lesson";
import {LessonTime} from "./lessonTime";
import {LessonInfo} from "./lessonInfo";
import {Day} from "./day";

export class TableModel {
  professors: ProfessorAccount[];
  groups: Group[];
  faculties: Faculty[];
  roles: Role[];
  lessons: Lesson[];
  lessonTimes: LessonTime[];
  lessonInfos: LessonInfo[];
  studyDays: Day[]
}
