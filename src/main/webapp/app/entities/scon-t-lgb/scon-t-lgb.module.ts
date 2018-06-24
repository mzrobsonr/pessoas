import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTLgbComponent,
    SconTLgbDetailComponent,
    SconTLgbUpdateComponent,
    SconTLgbDeletePopupComponent,
    SconTLgbDeleteDialogComponent,
    sconTLgbRoute,
    sconTLgbPopupRoute
} from './';

const ENTITY_STATES = [...sconTLgbRoute, ...sconTLgbPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTLgbComponent,
        SconTLgbDetailComponent,
        SconTLgbUpdateComponent,
        SconTLgbDeleteDialogComponent,
        SconTLgbDeletePopupComponent
    ],
    entryComponents: [SconTLgbComponent, SconTLgbUpdateComponent, SconTLgbDeleteDialogComponent, SconTLgbDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTLgbModule {}
