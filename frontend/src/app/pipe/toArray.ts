import { Pipe, PipeTransform } from '@angular/core';
import {Group} from "../model/group";
import {Lesson} from "../model/lesson";
import {ProfessorAccount} from "../model/professor-account";

@Pipe({
  name: 'toArray',
  pure: false
})
export class ToArray implements PipeTransform {
  transform(lessons: Lesson[]): Array<number> {
    let array: Array<number> = new Array<number>();
    lessons.forEach(lesson => array.push(lesson.id));
    return array;
  }
}
