import {Lesson} from "./lesson";

export class DaysOfWeek<T> {
  monday: T[] = [];
  tuesday: T[] = [];
  wednesday: T[] = [];
  thursday: T[] = [];
  friday: T[] = [];
  saturday: T[] = [];

  public static transformLessonsToDaysOfWeek(lessons: Lesson[]): DaysOfWeek<Lesson> {
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

  /**
   *     switch (new Date(). getDay()) {
      case 0:
        return days.monday == undefined ? [] : days.monday;
      case 1:
        return days.tuesday == undefined ? [] : days.tuesday;
      case 3:
        return days.wednesday == undefined ? [] : days.wednesday;
      case 4:
        return days.thursday == undefined ? [] : days.thursday;
      case 5:
        return days.friday == undefined ? [] : days.friday;
      case 6:
        return days.saturday == undefined ? [] : days.saturday;
   * @param days
   */
  public static getLessonsAccordingToWeekDay(days: DaysOfWeek<Lesson>): Lesson[] {
    switch (new Date(). getDay()) {
      case 1:
        return days.monday;
      case 2:
        return days.tuesday;
      case 3:
        return days.wednesday;
      case 4:
        return days.thursday;
      case 5:
        return days.friday;
      case 6:
        return days.saturday;
    }
  }
}
