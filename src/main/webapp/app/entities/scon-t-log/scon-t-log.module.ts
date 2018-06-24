import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTLogComponent,
    SconTLogDetailComponent,
    SconTLogUpdateComponent,
    SconTLogDeletePopupComponent,
    SconTLogDeleteDialogComponent,
    sconTLogRoute,
    sconTLogPopupRoute
} from './';

const ENTITY_STATES = [...sconTLogRoute, ...sconTLogPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTLogComponent,
        SconTLogDetailComponent,
        SconTLogUpdateComponent,
        SconTLogDeleteDialogComponent,
        SconTLogDeletePopupComponent
    ],
    entryComponents: [SconTLogComponent, SconTLogUpdateComponent, SconTLogDeleteDialogComponent, SconTLogDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTLogModule {}
