import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTLgb } from 'app/shared/model/scon-t-lgb.model';
import { SconTLgbService } from './scon-t-lgb.service';
import { SconTLgbComponent } from './scon-t-lgb.component';
import { SconTLgbDetailComponent } from './scon-t-lgb-detail.component';
import { SconTLgbUpdateComponent } from './scon-t-lgb-update.component';
import { SconTLgbDeletePopupComponent } from './scon-t-lgb-delete-dialog.component';
import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';

@Injectable({ providedIn: 'root' })
export class SconTLgbResolve implements Resolve<ISconTLgb> {
    constructor(private service: SconTLgbService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTLgb: HttpResponse<SconTLgb>) => sconTLgb.body);
        }
        return Observable.of(new SconTLgb());
    }
}

export const sconTLgbRoute: Routes = [
    {
        path: 'scon-t-lgb',
        component: SconTLgbComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLgb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-lgb/:id/view',
        component: SconTLgbDetailComponent,
        resolve: {
            sconTLgb: SconTLgbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLgb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-lgb/new',
        component: SconTLgbUpdateComponent,
        resolve: {
            sconTLgb: SconTLgbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLgb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-lgb/:id/edit',
        component: SconTLgbUpdateComponent,
        resolve: {
            sconTLgb: SconTLgbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLgb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTLgbPopupRoute: Routes = [
    {
        path: 'scon-t-lgb/:id/delete',
        component: SconTLgbDeletePopupComponent,
        resolve: {
            sconTLgb: SconTLgbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLgb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
