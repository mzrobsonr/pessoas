import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTTlgComponent,
    SconTTlgDetailComponent,
    SconTTlgUpdateComponent,
    SconTTlgDeletePopupComponent,
    SconTTlgDeleteDialogComponent,
    sconTTlgRoute,
    sconTTlgPopupRoute
} from './';

const ENTITY_STATES = [...sconTTlgRoute, ...sconTTlgPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTTlgComponent,
        SconTTlgDetailComponent,
        SconTTlgUpdateComponent,
        SconTTlgDeleteDialogComponent,
        SconTTlgDeletePopupComponent
    ],
    entryComponents: [SconTTlgComponent, SconTTlgUpdateComponent, SconTTlgDeleteDialogComponent, SconTTlgDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTTlgModule {}
