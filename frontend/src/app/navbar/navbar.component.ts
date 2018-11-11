import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {FormControl} from '@angular/forms';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input() seeTables: boolean;
  @Output() logout: EventEmitter<boolean> = new EventEmitter<boolean>();

  user: {role: string};

  constructor(private formBuilder: FormBuilder) { this.user = {role: 'Administrator'}; }

  ngOnInit() {
  }

  public logoutEvent(): void {
    this.logout.emit(false);
  }
}
