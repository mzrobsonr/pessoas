import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTPaiComponent,
    SconTPaiDetailComponent,
    SconTPaiUpdateComponent,
    SconTPaiDeletePopupComponent,
    SconTPaiDeleteDialogComponent,
    sconTPaiRoute,
    sconTPaiPopupRoute
} from './';

const ENTITY_STATES = [...sconTPaiRoute, ...sconTPaiPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTPaiComponent,
        SconTPaiDetailComponent,
        SconTPaiUpdateComponent,
        SconTPaiDeleteDialogComponent,
        SconTPaiDeletePopupComponent
    ],
    entryComponents: [SconTPaiComponent, SconTPaiUpdateComponent, SconTPaiDeleteDialogComponent, SconTPaiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTPaiModule {}
