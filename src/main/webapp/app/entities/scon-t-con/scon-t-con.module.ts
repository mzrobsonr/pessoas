import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTConComponent,
    SconTConDetailComponent,
    SconTConUpdateComponent,
    SconTConDeletePopupComponent,
    SconTConDeleteDialogComponent,
    sconTConRoute,
    sconTConPopupRoute
} from './';

const ENTITY_STATES = [...sconTConRoute, ...sconTConPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTConComponent,
        SconTConDetailComponent,
        SconTConUpdateComponent,
        SconTConDeleteDialogComponent,
        SconTConDeletePopupComponent
    ],
    entryComponents: [SconTConComponent, SconTConUpdateComponent, SconTConDeleteDialogComponent, SconTConDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTConModule {}
