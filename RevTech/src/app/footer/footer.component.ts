import { Component, OnInit } from '@angular/core';
import { faCopyright} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  // Current year for nav bar
  public year = new Date().getFullYear();
  public faCopyright = faCopyright;

  constructor() { }

  ngOnInit(): void {
  }

}
