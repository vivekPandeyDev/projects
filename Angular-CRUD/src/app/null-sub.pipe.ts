import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nullSub',
})
export class NullSubPipe implements PipeTransform {
  transform(value: string, substituteValue: any = 'empty'): string {
    return value === null || value === undefined || value.length === 0
      ? substituteValue
      : value;
  }
}
