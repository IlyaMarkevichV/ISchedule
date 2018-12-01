import { Pipe, PipeTransform } from '@angular/core';
import {Group} from "../model/group";
import {Lesson} from "../model/lesson";

@Pipe({
  name: 'filterLessonsByGroup',
  pure: false
})
export class FilterLessonsByGroupPipe implements PipeTransform {
  transform(lessons: Lesson[], group: Group): any {
    if (!lessons || !group) {
      return lessons;
    }
    // filter items array, items which match and return true will be
    // kept, false will be filtered out
    return lessons.filter(lesson => {
      for (let gr of lesson.groups){
        if (gr.id == group.id) {
          return true;
        }
      }
      return false;
    });
  }
}
