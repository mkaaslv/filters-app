import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timestampToDate',
  standalone: true
})
export class TimestampToDatePipe implements PipeTransform {

  constructor(private datePipe: DatePipe) {}

  transform(timestamp: number, format: string = 'dd/MM/yyyy HH:mm:ss'): string {
    return this.datePipe.transform(timestamp, format) || '';
  }
}
