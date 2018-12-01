export class Faculty {
  id: number;
  facultyName: string;

  static cloneFaculty(faculty: Faculty): Faculty {
    let prototype = new Faculty();
    prototype.id = faculty.id;
    prototype.facultyName = faculty.facultyName;
    return prototype;
  }
}
