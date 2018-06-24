import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTBaiComponent,
    SconTBaiDetailComponent,
    SconTBaiUpdateComponent,
    SconTBaiDeletePopupComponent,
    SconTBaiDeleteDialogComponent,
    sconTBaiRoute,
    sconTBaiPopupRoute
} from './';

const ENTITY_STATES = [...sconTBaiRoute, ...sconTBaiPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTBaiComponent,
        SconTBaiDetailComponent,
        SconTBaiUpdateComponent,
        SconTBaiDeleteDialogComponent,
        SconTBaiDeletePopupComponent
    ],
    entryComponents: [SconTBaiComponent, SconTBaiUpdateComponent, SconTBaiDeleteDialogComponent, SconTBaiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTBaiModule {}
