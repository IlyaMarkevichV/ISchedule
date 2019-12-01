import { Pipe, PipeTransform } from '@angular/core';
import {Group} from "../model/group";
import {Lesson} from "../model/lesson";
import {ProfessorAccount} from "../model/professor-account";

@Pipe({
  name: 'filterLessonsByProfessor',
  pure: false
})
export class FilterLessonsByProfessorPipe implements PipeTransform {
  transform(lessons: Lesson[], professor: ProfessorAccount): any {
    if (!lessons || !professor) {
      return lessons;
    }
    // filter items array, items which match and return true will be
    // kept, false will be filtered out
    return lessons.filter(lesson => {
      return lesson.professor.id == professor.id;
    });
  }
}
