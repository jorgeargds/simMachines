
import { NgModule } from '@angular/core';

import { TableComponent } from './table.component/table.component';
import {BrowserModule} from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';

@NgModule({
  declarations: [
    TableComponent
  ],
  imports: [
    BrowserModule,
    DataTablesModule
  ],
  exports: [TableComponent],
  entryComponents: [TableComponent]
  })
export class ComponentsModule { }
