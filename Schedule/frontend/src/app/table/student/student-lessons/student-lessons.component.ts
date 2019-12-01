import { Component, OnInit } from '@angular/core';
import {AuthorizationAndTransmitService} from "../../../service/authorization-and-transmit.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Lesson} from "../../../model/lesson";

@Component({
  selector: 'student-lessons',
  templateUrl: './student-lessons.component.html',
  styleUrls: ['./student-lessons.component.css']
})
export class StudentLessonsComponent implements OnInit {

  public studentLessons: Lesson[] = [];

  constructor(
    private authService: AuthorizationAndTransmitService,
    private loadingService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.loadingService.show();
    this.authService.currentStudentLessons.subscribe(req => {
      this.studentLessons = req.filter(function(item, pos) {
        return req.indexOf(item) == pos;
      });

      this.loadingService.hide();
    })
  }

}
