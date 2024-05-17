import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  private openNonModalSubject = new BehaviorSubject<boolean>(false);
  openNonModal$: Observable<boolean> = this.openNonModalSubject.asObservable();

  constructor() { }

  setOpenNonModal(value: boolean) {
    this.openNonModalSubject.next(value);
  }
}