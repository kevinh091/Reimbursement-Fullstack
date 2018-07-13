import { Component, Input,Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-description-dialog',
  templateUrl: './description-dialog.component.html',
  styleUrls: ['./description-dialog.component.css']
})
export class DescriptionDialogComponent {
  @Input() message:string;
  @Output() messageEvent = new EventEmitter<boolean>();
  constructor() {
   }

   sendMessage() {
    this.messageEvent.emit(true);
  }
}
