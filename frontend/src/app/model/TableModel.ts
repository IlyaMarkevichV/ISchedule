import {StudentAccount} from "./student-account";
import {ProfessorAccount} from "./professor-account";
import {Group} from "./group";
import {Faculty} from "./faculty";

export class TableModel {
  students: StudentAccount[];
  professors: ProfessorAccount[];
  groups: Group[];
  faculties: Faculty[];
}
