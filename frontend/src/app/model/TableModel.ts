import {StudentAccount} from "./student-account";
import {ProfessorAccount} from "./professor-account";
import {Group} from "./group";
import {Faculty} from "./faculty";
import {OnDestroy, OnInit} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";

export class TableModel {
  students: StudentAccount[];
  professors: ProfessorAccount[];
  groups: Group[];
  faculties: Faculty[];
}
