import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {FormControl} from '@angular/forms';
import {TokenStorage} from "../service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public router: Router,
              private tokeStorage: TokenStorage) { }

  ngOnInit() {
  }

  public logout(): void {
    this.tokeStorage.signOut();
    this.router.navigate(['/login']);
  }
}
