import { Component, signal } from '@angular/core';
import { PageFooter } from "./components/page-footer/page-footer";
import { PageHeader } from "./components/page-header/page-header";
import { RouterOutlet } from "@angular/router"


@Component({
  selector: 'app-root',
  imports: [
    PageFooter,
    PageHeader,
    RouterOutlet
],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
