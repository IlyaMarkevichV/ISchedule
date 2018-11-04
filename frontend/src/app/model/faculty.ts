export class Faculty {
  facultyId: number;
  facultyName: string;

  static cloneFaculty(faculty: Faculty): Faculty {
    let prototype = new Faculty();
    prototype.facultyId = faculty.facultyId;
    prototype.facultyName = faculty.facultyName;
    return prototype;
  }
}
