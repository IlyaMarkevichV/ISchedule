import {Lesson} from "./lesson";

export class DaysOfWeek<T> {
  monday: T[] = [];
  tuesday: T[] = [];
  wednesday: T[] = [];
  thursday: T[] = [];
  friday: T[] = [];
  saturday: T[] = [];

  public static transformLessonToDaysOfWeek(lessons: Lesson[]): DaysOfWeek<Lesson> {
    let days: DaysOfWeek<Lesson> = new DaysOfWeek<Lesson>();
    for (let lesson of lessons) {
      switch (lesson.day.dayName) {
        case "Monday":
          days.monday.push(lesson);
          break;
        case "Tuesday":
          days.tuesday.push(lesson);
          break;
        case "Wednesday":
          days.wednesday.push(lesson);
          break;
        case "Thursday":
          days.thursday.push(lesson);
          break;
        case "Friday":
          days.friday.push(lesson);
          break;
        case "Saturday":
          days.saturday.push(lesson);
      }
    }
    return days;
  }
}
