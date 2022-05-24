import { Injectable } from '@angular/core';
import { gsap } from 'gsap';

@Injectable({
  providedIn: 'root'
})
export class GsapService {

  constructor() { }

  public fadeIn(e: any, tym: any, alfa: any, dlay: any){
    gsap.from(e,{duration: tym, opacity: alfa, delay: dlay});
  }

}
