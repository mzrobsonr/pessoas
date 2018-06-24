import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTBacComponent,
    SconTBacDetailComponent,
    SconTBacUpdateComponent,
    SconTBacDeletePopupComponent,
    SconTBacDeleteDialogComponent,
    sconTBacRoute,
    sconTBacPopupRoute
} from './';

const ENTITY_STATES = [...sconTBacRoute, ...sconTBacPopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTBacComponent,
        SconTBacDetailComponent,
        SconTBacUpdateComponent,
        SconTBacDeleteDialogComponent,
        SconTBacDeletePopupComponent
    ],
    entryComponents: [SconTBacComponent, SconTBacUpdateComponent, SconTBacDeleteDialogComponent, SconTBacDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTBacModule {}
