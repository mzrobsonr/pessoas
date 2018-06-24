import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTUfe } from 'app/shared/model/scon-t-ufe.model';
import { SconTUfeService } from './scon-t-ufe.service';
import { SconTUfeComponent } from './scon-t-ufe.component';
import { SconTUfeDetailComponent } from './scon-t-ufe-detail.component';
import { SconTUfeUpdateComponent } from './scon-t-ufe-update.component';
import { SconTUfeDeletePopupComponent } from './scon-t-ufe-delete-dialog.component';
import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';

@Injectable({ providedIn: 'root' })
export class SconTUfeResolve implements Resolve<ISconTUfe> {
    constructor(private service: SconTUfeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTUfe: HttpResponse<SconTUfe>) => sconTUfe.body);
        }
        return Observable.of(new SconTUfe());
    }
}

export const sconTUfeRoute: Routes = [
    {
        path: 'scon-t-ufe',
        component: SconTUfeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTUfe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-ufe/:id/view',
        component: SconTUfeDetailComponent,
        resolve: {
            sconTUfe: SconTUfeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTUfe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-ufe/new',
        component: SconTUfeUpdateComponent,
        resolve: {
            sconTUfe: SconTUfeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTUfe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-ufe/:id/edit',
        component: SconTUfeUpdateComponent,
        resolve: {
            sconTUfe: SconTUfeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTUfe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTUfePopupRoute: Routes = [
    {
        path: 'scon-t-ufe/:id/delete',
        component: SconTUfeDeletePopupComponent,
        resolve: {
            sconTUfe: SconTUfeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTUfe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
