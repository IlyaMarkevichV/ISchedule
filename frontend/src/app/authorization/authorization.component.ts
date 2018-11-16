import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Router} from "@angular/router";
import {TableModelService} from "../service/table-model.service";

@Component({
  selector: 'authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  @Output()
  public authorized: EventEmitter<boolean> = new EventEmitter<boolean>();

  public login: string;
  public password: string;

  public user: string;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private router: Router) {
  }


  ngOnInit() {
  }

  public loginFunction(): void {
    this.loadingService.show();
    setTimeout(() => {
        this.authorized.emit(true);
        this.user = "administrator";
        this.router.navigate(['administrator'])
      }
      , 1500);
    this.loadingService.hide();
  }



}
