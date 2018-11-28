import {LessonInfo} from "./lessonInfo";
import {ProfessorAccount} from "./professor-account";
import {LessonTime} from "./lessonTime";
import {Day} from "./day";
import {Group} from "./group";

export class Lesson {
  lessonId: number;
  lessonInfo: LessonInfo;
  professor: ProfessorAccount;
  lessonTime: LessonTime;
  lessonRoom: number;
  day: Day;
  groups: Group[];

  static cloneLesson(lesson: Lesson): Lesson {
    let cloned: Lesson = new Lesson();
    cloned.lessonId = lesson.lessonId;
    cloned.lessonInfo = lesson.lessonInfo;
    cloned.professor = lesson.professor;
    cloned.lessonTime = lesson.lessonTime;
    cloned.lessonRoom = lesson.lessonRoom;
    cloned.day = lesson.day;
    cloned.groups = cloned.groups;
    return cloned;
  }
}
