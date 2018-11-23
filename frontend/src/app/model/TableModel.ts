import {StudentAccount} from "./student-account";
import {Group} from "./group";
import {Faculty} from "./faculty";
import {ProfessorAccount} from "./professor-account";
import {Role} from "./role";

export class TableModel {
  students: StudentAccount[];
  professors: ProfessorAccount[];
  groups: Group[];
  faculties: Faculty[];
  roles: Role[];
}
