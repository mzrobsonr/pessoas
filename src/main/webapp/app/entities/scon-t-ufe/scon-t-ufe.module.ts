import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PessoasSharedModule } from 'app/shared';
import {
    SconTUfeComponent,
    SconTUfeDetailComponent,
    SconTUfeUpdateComponent,
    SconTUfeDeletePopupComponent,
    SconTUfeDeleteDialogComponent,
    sconTUfeRoute,
    sconTUfePopupRoute
} from './';

const ENTITY_STATES = [...sconTUfeRoute, ...sconTUfePopupRoute];

@NgModule({
    imports: [PessoasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SconTUfeComponent,
        SconTUfeDetailComponent,
        SconTUfeUpdateComponent,
        SconTUfeDeleteDialogComponent,
        SconTUfeDeletePopupComponent
    ],
    entryComponents: [SconTUfeComponent, SconTUfeUpdateComponent, SconTUfeDeleteDialogComponent, SconTUfeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasSconTUfeModule {}
