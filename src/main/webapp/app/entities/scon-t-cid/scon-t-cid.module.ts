import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTCidComponent,
    SconTCidDetailComponent,
    SconTCidUpdateComponent,
    SconTCidDeletePopupComponent,
    SconTCidDeleteDialogComponent,
    sconTCidRoute,
    sconTCidPopupRoute
} from './';

const ENTITY_STATES = [...sconTCidRoute, ...sconTCidPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTCidComponent,
        SconTCidDetailComponent,
        SconTCidUpdateComponent,
        SconTCidDeleteDialogComponent,
        SconTCidDeletePopupComponent
    ],
    entryComponents: [SconTCidComponent, SconTCidUpdateComponent, SconTCidDeleteDialogComponent, SconTCidDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTCidModule {}
