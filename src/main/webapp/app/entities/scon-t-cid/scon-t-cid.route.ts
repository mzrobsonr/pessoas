import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTCid } from 'app/shared/model/scon-t-cid.model';
import { SconTCidService } from './scon-t-cid.service';
import { SconTCidComponent } from './scon-t-cid.component';
import { SconTCidDetailComponent } from './scon-t-cid-detail.component';
import { SconTCidUpdateComponent } from './scon-t-cid-update.component';
import { SconTCidDeletePopupComponent } from './scon-t-cid-delete-dialog.component';
import { ISconTCid } from 'app/shared/model/scon-t-cid.model';

@Injectable({ providedIn: 'root' })
export class SconTCidResolve implements Resolve<ISconTCid> {
    constructor(private service: SconTCidService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTCid: HttpResponse<SconTCid>) => sconTCid.body);
        }
        return Observable.of(new SconTCid());
    }
}

export const sconTCidRoute: Routes = [
    {
        path: 'scon-t-cid',
        component: SconTCidComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCid.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-cid/:id/view',
        component: SconTCidDetailComponent,
        resolve: {
            sconTCid: SconTCidResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCid.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-cid/new',
        component: SconTCidUpdateComponent,
        resolve: {
            sconTCid: SconTCidResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCid.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-cid/:id/edit',
        component: SconTCidUpdateComponent,
        resolve: {
            sconTCid: SconTCidResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCid.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTCidPopupRoute: Routes = [
    {
        path: 'scon-t-cid/:id/delete',
        component: SconTCidDeletePopupComponent,
        resolve: {
            sconTCid: SconTCidResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCid.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
