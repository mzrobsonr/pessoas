import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTBac } from 'app/shared/model/scon-t-bac.model';
import { SconTBacService } from './scon-t-bac.service';
import { SconTBacComponent } from './scon-t-bac.component';
import { SconTBacDetailComponent } from './scon-t-bac-detail.component';
import { SconTBacUpdateComponent } from './scon-t-bac-update.component';
import { SconTBacDeletePopupComponent } from './scon-t-bac-delete-dialog.component';
import { ISconTBac } from 'app/shared/model/scon-t-bac.model';

@Injectable({ providedIn: 'root' })
export class SconTBacResolve implements Resolve<ISconTBac> {
    constructor(private service: SconTBacService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTBac: HttpResponse<SconTBac>) => sconTBac.body);
        }
        return Observable.of(new SconTBac());
    }
}

export const sconTBacRoute: Routes = [
    {
        path: 'scon-t-bac',
        component: SconTBacComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBac.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-bac/:id/view',
        component: SconTBacDetailComponent,
        resolve: {
            sconTBac: SconTBacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBac.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-bac/new',
        component: SconTBacUpdateComponent,
        resolve: {
            sconTBac: SconTBacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBac.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-bac/:id/edit',
        component: SconTBacUpdateComponent,
        resolve: {
            sconTBac: SconTBacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBac.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTBacPopupRoute: Routes = [
    {
        path: 'scon-t-bac/:id/delete',
        component: SconTBacDeletePopupComponent,
        resolve: {
            sconTBac: SconTBacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBac.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
