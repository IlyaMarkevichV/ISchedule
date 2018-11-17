import {StudentAccount} from "./student-account";
import {Group} from "./group";
import {Faculty} from "./faculty";
import {UserAccount} from "./UserAccount";

export class TableModel {
  students: UserAccount[];
  professors: UserAccount[];
  groups: Group[];
  faculties: Faculty[];
}
